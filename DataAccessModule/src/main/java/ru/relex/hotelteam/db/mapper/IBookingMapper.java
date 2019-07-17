package ru.relex.hotelteam.db.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.relex.hotelteam.db.domain.Booking;

@Mapper
public interface IBookingMapper {

  Booking createBooking(Booking booking);

  Optional<Booking> getBookingById(int id);

  List<Booking> listBookings();

  List<Booking> listBookingsByUserId(@Param("userId") int userId);

  void deleteBooking(@Param("id") int id);

  void updateBooking(Booking booking);
}
