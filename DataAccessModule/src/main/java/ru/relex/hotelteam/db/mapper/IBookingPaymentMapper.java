package ru.relex.hotelteam.db.mapper;

import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.relex.hotelteam.db.domain.BookingPayment;

@Mapper
public interface IBookingPaymentMapper {

  BookingDtoPayment createPayment(BookingPayment bookingPayment);

  Optional<BookingDtoPayment> getPaymentByUserId(@Param("userId") int userId);

  Optional<BookingDtoPayment> getPaymentByBookingId(@Param("bookingId") int bookingId);

  List<BookingDtoPayment> listPaymentsByUserId(@Param("userId") int userId);

  List<BookingDtoPayment> listPaymentsByBookingId(@Param("bookingId") int bookingId);

  void deletePayment(@Param("id") int id);

  void updatePayment(BookingPayment payment);

}
