package ru.relex.hotelteam.service.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Supplier;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.domain.BookingPayment;
import ru.relex.hotelteam.db.mapper.IBookingPaymentMapper;
import ru.relex.hotelteam.service.IBookingPaymentService;
import ru.relex.hotelteam.service.dto.BookingPaymentDto;
import ru.relex.hotelteam.service.mapstruct.IBookingPaymentMapstruct;

/**
 * Created by Tarasov Ivan on 20/07/2019 Time: 19:54
 */
public class BookingPaymentServiceImpl implements IBookingPaymentService {

  private final IBookingPaymentMapper mapper;
  private final IBookingPaymentMapstruct mapstruct;

  public BookingPaymentServiceImpl(IBookingPaymentMapper mapper,
      IBookingPaymentMapstruct mapstruct) {
    this.mapper = mapper;
    this.mapstruct = mapstruct;
  }

  @Override
  public BookingPaymentDto createPayment(Booking booking) {
    BookingPayment payment = new BookingPayment();

    payment.setBookingId(booking.getId());
    payment.setRoomId(booking.getRoomId());
    payment.setUserId(booking.getUserId());

    long days = (booking.getCheckInDate().toEpochSecond()
        - booking.getCheckOutDate().toEpochSecond()) /(24*60*60);

    payment.setAmountOfReservedDays((int) days);
    payment.setTimePayment(OffsetDateTime.now());

    return mapstruct.toDto(mapper.createPayment(payment));
  }

  @Override
  public BookingPaymentDto findById(int id) {
    return mapstruct.toDto(mapper.getPaymentById(id)
        .orElseThrow(notFound("\"No payment [ id = \" + id + \" ] was found!\"")));
  }

  @Override
  public List<BookingPaymentDto> listPayments() {
    return null;
  }

  @Override
  public List<BookingPaymentDto> listPaymentsByUserId(int userId) {
    return null;
  }

  @Override
  public List<BookingPaymentDto> listPaymentsByBookingId(int bookingId) {
    return null;
  }

  @Override
  public void deletePayment(int id) {

  }

  @Override
  public void updatePayment(BookingPayment payment) {

  }


  private Supplier<RuntimeException> notFound(String s) {
    return () -> new RuntimeException(s);
  }
}
