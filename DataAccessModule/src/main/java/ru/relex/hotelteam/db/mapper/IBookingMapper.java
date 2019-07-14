package ru.relex.hotelteam.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.relex.hotelteam.db.domain.Booking;

import java.util.List;
import java.util.Optional;

@Mapper
public interface IBookingMapper {

    Booking createBooking(Booking booking);

    Optional<Booking> getBookingById(int id);

    List<Booking> listBookings();

    void deleteUser(@Param("id") int id);

    void updateBooking(Booking booking);
}
