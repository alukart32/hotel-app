package ru.relex.hotelteam;

import java.time.LocalDateTime;
import java.util.List;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.domain.BookingPayment;
import ru.relex.hotelteam.dto.bookings.BookingPaymentDto;

public interface IBookingPaymentService {

  BookingPaymentDto createPayment(Booking booking);

  BookingPaymentDto findById(int id);

  List<BookingPaymentDto> listPayments();

  List<BookingPaymentDto> listPaymentsByUserId(int userId);

  BookingPaymentDto getPaymentByBookingId(int bookingId);

  void deletePayment(int id);

  void deletePaymentByBookingId(int bookingId);

  void updatePayment(BookingPayment payment);

  void updatePaymentForCheckIn(int bookingId, LocalDateTime date);
}
