package ru.relex.hotelteam.service;

import java.util.List;
import ru.relex.hotelteam.service.dto.BookingCreateDTO;
import ru.relex.hotelteam.service.dto.BookingDTO;
import ru.relex.hotelteam.service.dto.BookingUpdateDTO;

public interface IBookingService {

  BookingDTO createBooking(BookingCreateDTO booking);

  BookingDTO findById(int id);

  List<BookingDTO> listBookings();

  List<BookingDTO> listBookingsByUserId(int userId);

  void delete(int id);

  void update(int id, BookingUpdateDTO updatedBooking);
}
