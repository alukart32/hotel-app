package ru.relex.hotelteam.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.IBookingService;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.mapper.IBookingMapper;
import ru.relex.hotelteam.dto.bookings.BookingCreateDto;
import ru.relex.hotelteam.dto.bookings.BookingDto;
import ru.relex.hotelteam.dto.bookings.BookingPaymentDto;
import ru.relex.hotelteam.dto.bookings.BookingRegisterDto;
import ru.relex.hotelteam.dto.bookings.BookingUpdateDateDto;
import ru.relex.hotelteam.exceptions.CreateBookingException;
import ru.relex.hotelteam.exceptions.EntityNotFoundException;
import ru.relex.hotelteam.exceptions.RegisterGuestException;
import ru.relex.hotelteam.service.mapstruct.IBookingMapstruct;

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
    List<Booking> booking = mapper.getBookingByRoomIdBetweenDates(bookingDto.getRoomId()
        , bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
    if (booking.isEmpty()) {

      Booking newBooking = mapper.createBooking(mapstruct.fromCreateDto(bookingDto));
      paymentService.createPayment(newBooking);

      return mapstruct.toDto(newBooking);
    } else {
      throw new CreateBookingException();
    }
  }

  @Override
  public BookingDto findById(int id) throws EntityNotFoundException {
    Booking booking = mapper.getBookingById(id)
        .orElseThrow(() -> new EntityNotFoundException("Booking", id));
    return mapstruct.toDto(booking);
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
      throws RegisterGuestException, EntityNotFoundException {

    Booking booking = mapper.getBookingById(registerDto.getBookingId())
        .orElseThrow(() -> new EntityNotFoundException("User", registerDto.getBookingId()));

    if (booking.getRealCheckInDate() == null) {
      BookingUpdateDateDto dates = new BookingUpdateDateDto();
      dates.setRealCheckInDate(LocalDateTime.now());

      updateRealCheckDate(booking.getId(), dates);
      paymentService.updatePaymentForCheckIn(booking.getId(), LocalDateTime.now());
    }
  }

  @Override
  public void checkOut(int id)
      throws EntityNotFoundException {
    Booking booking = mapper.getBookingById(id)
        .orElseThrow(() -> new EntityNotFoundException("User", id));

    BookingUpdateDateDto dates = new BookingUpdateDateDto();
    dates.setRealCheckOutDate(LocalDateTime.now());

    updateRealCheckDate(booking.getId(), dates);
  }

  @Override
  public void cancel(int id) {
    Booking booking = mapper.getBookingById(id)
        .orElseThrow(() -> new EntityNotFoundException("User", id));
    BookingPaymentDto payment = paymentService.getPaymentByBookingId(id);
    if (payment == null) {
      delete(id);
    }
  }

  @Override
  public void delete(int id) {
    BookingDto booking = findById(id);

    paymentService.deletePaymentByBookingId(id);
    mapper.deleteBooking(id);
  }

  private void updateRealCheckDate(int id, BookingUpdateDateDto dto) {
    mapper.updateRealCheckDate(id, dto.getRealCheckInDate(), dto.getRealCheckOutDate());
  }
}
