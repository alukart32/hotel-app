package ru.relex.hotelteam.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.mapper.IBookingMapper;
import ru.relex.hotelteam.IBookingService;
import ru.relex.hotelteam.dto.bookings.BookingCreateDto;
import ru.relex.hotelteam.dto.bookings.BookingDto;
import ru.relex.hotelteam.dto.bookings.BookingPaymentDto;
import ru.relex.hotelteam.dto.bookings.BookingRegisterDto;
import ru.relex.hotelteam.dto.bookings.BookingUpdateDateDto;
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
      if (booking.getRealCheckInDate() == null) {
        BookingUpdateDateDto dates = new BookingUpdateDateDto();
        dates.setRealCheckInDate(LocalDateTime.now());

        updateRealCheckDate(booking.getId(), dates);
        paymentService.updatePaymentForCheckIn(booking.getId(), LocalDateTime.now());
      }
    } else {
      throw new RegisterGuestDateException("Registration date is out of booking dates");
    }
  }

  @Override
  public void checkOut(int bookingId)
      throws UserNotFoundException, BookingNotFoundException {
    Booking booking = mapper.getBookingById(bookingId);

    if (booking != null) {
      BookingUpdateDateDto dates = new BookingUpdateDateDto();
      dates.setRealCheckOutDate(LocalDateTime.now());

      updateRealCheckDate(booking.getId(), dates);
    } else {
      throw new UserNotFoundException();
    }
  }

  @Override
  public void cancel(int bookingId) {
    Booking booking = mapper.getBookingById(bookingId);

    if (booking != null) {
      BookingPaymentDto payment = paymentService.getPaymentByBookingId(bookingId);

      if (payment == null) {
        delete(bookingId);
      }
    }
  }

  @Override
  public void delete(int id) {
    paymentService.deletePaymentByBookingId(id);
    mapper.deleteBooking(id);
  }

  private void updateRealCheckDate(int id, BookingUpdateDateDto dto) {
    mapper.updateRealCheckDate(id, dto.getRealCheckInDate(), dto.getRealCheckOutDate());
  }
}
