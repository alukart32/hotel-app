package ru.relex.hotelteam.db.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.relex.hotelteam.db.domain.Booking;

@Mapper
public interface IBookingMapper {

  Booking createBooking(Booking booking);

  Booking getBookingById(int id);

  List<Booking> listBookings();

  List<Booking> listBookingsByUserId(@Param("userId") int userId);

  List<Booking> listBookingsByRoomId(int roomId);

  void deleteBooking(@Param("id") int id);

  void updateBooking(Booking booking);

  Booking getBookingByRoomIdBetweenDates(int roomId, LocalDateTime from, LocalDateTime to);
}
