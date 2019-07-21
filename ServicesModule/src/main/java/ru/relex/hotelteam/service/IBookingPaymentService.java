package ru.relex.hotelteam.service;

import java.util.List;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.domain.BookingPayment;
import ru.relex.hotelteam.service.dto.BookingPaymentDto;

public interface IBookingPaymentService {

  BookingPaymentDto createPayment(Booking booking);

  BookingPaymentDto findById(int id);

  List<BookingPaymentDto> listPayments();

  List<BookingPaymentDto> listPaymentsByUserId(int userId);

  List<BookingPaymentDto> listPaymentsByBookingId(int bookingId);

  void deletePayment(int id);

  void updatePayment(BookingPayment payment);
}
