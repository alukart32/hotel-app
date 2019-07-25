package ru.relex.hotelteam.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.domain.BookingPayment;
import ru.relex.hotelteam.db.mapper.IBookingPaymentMapper;
import ru.relex.hotelteam.IBookingPaymentService;
import ru.relex.hotelteam.dto.bookings.BookingPaymentDto;
import ru.relex.hotelteam.service.mapstruct.IBookingPaymentMapstruct;

/**
 * Created by Tarasov Ivan on 20/07/2019 Time: 19:54.
 */
@Service
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

    long days = ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());

    payment.setAmountOfDays((int) days + 1);

    return mapstruct.toDto(mapper.createPayment(payment));
  }

  @Override
  public BookingPaymentDto findById(int id) {
    return mapstruct.toDto(mapper.getPaymentById(id)
        .orElseThrow());
  }

  @Override
  public List<BookingPaymentDto> listPayments() {
    return mapstruct.toDto(mapper.listPayments());
  }

  @Override
  public List<BookingPaymentDto> listPaymentsByUserId(int userId) {
    return mapstruct.toDto(mapper.listPaymentsByUserId(userId));
  }

  @Override
  public BookingPaymentDto getPaymentByBookingId(int bookingId) {
    return mapstruct.toDto(mapper.getPaymentByBookingId(bookingId));
  }

  @Override
  public void deletePayment(int id) {
    mapper.deletePayment(id);
  }

  @Override
  public void deletePaymentByBookingId(int bookingId) {
    mapper.deletePaymentByBookingId(bookingId);
  }

  @Override
  public void updatePayment(BookingPayment payment) {
    mapper.updatePayment(payment);
  }

  @Override
  public void updatePaymentForCheckIn(int bookingId, LocalDateTime date) {
    mapper.updatePaymentDateByBookingId(bookingId, date);
  }
}
