package ru.relex.hotelteam.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.mapper.IBookingMapper;
import ru.relex.hotelteam.service.IBookingService;
import ru.relex.hotelteam.service.dto.BookingCheckOutDto;
import ru.relex.hotelteam.service.dto.BookingCreateDto;
import ru.relex.hotelteam.service.dto.BookingDto;
import ru.relex.hotelteam.service.dto.BookingPaymentDto;
import ru.relex.hotelteam.service.dto.BookingRegisterDto;
import ru.relex.hotelteam.service.dto.BookingUpdateDateDto;
import ru.relex.hotelteam.service.dto.BookingUpdateDto;
import ru.relex.hotelteam.service.mapstruct.IBookingMapstruct;
import ru.relex.hotelteam.shared.exception.service.BookingNotFoundException;
import ru.relex.hotelteam.shared.exception.service.CreateBookingException;
import ru.relex.hotelteam.shared.exception.service.RegisterGuestDateException;
import ru.relex.hotelteam.shared.exception.service.UserNotFoundException;

@Service
public class BookingServiceImpl implements IBookingService {

  private final IBookingMapper mapper;
  private final IBookingMapstruct mapstruct;
  private final BookingPaymentServiceImpl paymentService;

  public BookingServiceImpl(IBookingMapper mapper, IBookingMapstruct mapstruct,
      BookingPaymentServiceImpl paymentService) {
    this.mapper = mapper;
    this.mapstruct = mapstruct;
    this.paymentService = paymentService;
  }

  @Override
  public BookingDto createBooking(BookingCreateDto bookingDto) throws CreateBookingException {
    Booking booking = mapper.getBookingByRoomIdBetweenDates(bookingDto.getRoomId()
        , bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
    if (booking == null) {

      Booking newBooking = mapper.createBooking(mapstruct.fromCreateDto(bookingDto));
      paymentService.createPayment(newBooking);

      return mapstruct.toDto(newBooking);
    } else {
      throw new CreateBookingException();
    }
  }

  @Override
  public BookingDto findById(int id) throws BookingNotFoundException {
    Booking booking = mapper.getBookingById(id);

    if (booking == null) {
      throw new BookingNotFoundException("Booking with id: " + id + " wasn't found");
    }
    return mapstruct.toDto(mapper.getBookingById(id));
  }

  @Override
  public List<BookingDto> listBookings() {
    return mapstruct.toDto(mapper.listBookings());
  }

  @Override
  public List<BookingDto> listBookingsByUserId(int userId) {
    return mapstruct.toDto(mapper.listBookingsByUserId(userId));
  }

  @Override
  public List<BookingDto> listBookingsByRoomId(int roomId) {
    return mapstruct.toDto(mapper.listBookingsByRoomId(roomId));
  }

  /**
   * Register a guest means that it needs to update a realCheckInDate field and create payment.
   *
   * @param registerDto the guest, date when he has checked in
   */
  @Override
  public void registration(BookingRegisterDto registerDto)
      throws RegisterGuestDateException, BookingNotFoundException {

    Booking booking = mapper.getBookingById(registerDto.getBookingId());

    if (booking != null) {
      BookingUpdateDateDto dates = new BookingUpdateDateDto();
      dates.setRealCheckInDate(booking.getRealCheckInDate());

      updateRealCheckDate(dates);

      paymentService.updatePaymentDateByBooking(booking.getId(), LocalDateTime.now());
    } else {
      throw new RegisterGuestDateException("Registration date is out of booking dates");
    }
  }

  @Override
  public void checkOutGuest(BookingCheckOutDto checkOutDto)
      throws UserNotFoundException, BookingNotFoundException {
    Booking currentBooking = mapstruct.fromDto(findBookingForCheckOut(checkOutDto));

    if (currentBooking != null) {
      currentBooking.setRealCheckOutDate(checkOutDto.getCheckOutDate());
      currentBooking.setUserId(checkOutDto.getUserId());

      update(currentBooking.getId(), mapstruct.toUpdateDto(currentBooking));
    } else {
      throw new UserNotFoundException("User with id: " + checkOutDto.getUserId() + " wasn't found");
    }
  }

  @Override
  public void cancel(int userId, int bookingId) {
    /**
     * 1) find guest's booking.
     * 2) is it paid
     *    2.1) no = delete
     *    2.2) yes = refund?
     */
    List<Booking> bookings = mapstruct.fromDto(listBookingsByUserId(userId));

    Booking booking = null;

    for (Booking b : bookings) {
      if (b.getId() == bookingId) {
        booking = b;
        break;
      }
    }

    if (booking != null) {
      BookingPaymentDto payment = paymentService.getPaymentByBookingId(bookingId);

      if (payment == null) {
        delete(bookingId);
      }
    }

    // кинем exception
  }

  @Override
  public void delete(int id) {
    paymentService.deletePaymentByBookingId(id);
    mapper.deleteBooking(id);
  }

  private void updateRealCheckDate(BookingUpdateDateDto dto){
    mapper.updateRealCheckDate(dto.getRealCheckInDate(), dto.getRealCheckOutDate());
  }
}
