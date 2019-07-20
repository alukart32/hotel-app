package ru.relex.hotelteam.service;

import java.util.List;
import ru.relex.hotelteam.service.dto.BookingCreateDto;
import ru.relex.hotelteam.service.dto.BookingDto;
import ru.relex.hotelteam.service.dto.BookingUpdateDto;

public interface IBookingService {

  BookingDto createBooking(BookingCreateDto booking);

  BookingDto findById(int id);

  List<BookingDto> listBookings();

  List<BookingDto> listBookingsByUserId(int userId);

  void delete(int id);

  void update(int id, BookingUpdateDto updatedBooking);

  List<BookingDto> listBookingsByRoomId(int roomId);
}
