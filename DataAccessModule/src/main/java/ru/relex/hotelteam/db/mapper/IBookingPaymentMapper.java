package ru.relex.hotelteam.db.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.relex.hotelteam.db.domain.BookingPayment;

@Mapper
public interface IBookingPaymentMapper {

  BookingPayment createPayment(BookingPayment bookingPayment);

  Optional<BookingPayment> getPaymentByUserId(@Param("userId") int userId);

  Optional<BookingPayment> getPaymentByBookingId(@Param("bookingId") int bookingId);

  List<BookingPayment> listPaymentsByUserId(@Param("userId") int userId);

  List<BookingPayment> listPaymentsByBookingId(@Param("bookingId") int bookingId);

  void deletePayment(@Param("id") int id);

  void updatePayment(BookingPayment payment);

}
