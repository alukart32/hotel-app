package ru.relex.hotelteam.service;

import java.util.List;
import ru.relex.hotelteam.service.dto.BookingCreateDto;
import ru.relex.hotelteam.service.dto.BookingDto;
import ru.relex.hotelteam.service.dto.BookingRegisterDto;
import ru.relex.hotelteam.service.dto.BookingUpdateDto;
import ru.relex.hotelteam.shared.exception.service.BookingNotFoundException;
import ru.relex.hotelteam.shared.exception.service.CreateBookingException;
import ru.relex.hotelteam.shared.exception.service.RegisterGuestDateException;

public interface IBookingService {

  BookingDto createBooking(BookingCreateDto booking) throws CreateBookingException;

  BookingDto findById(int id);

  List<BookingDto> listBookings();

  List<BookingDto> listBookingsByUserId(int userId);

  List<BookingDto> listBookingsByRoomId(int roomId);

  void registerGuest(BookingRegisterDto registerDto) throws RegisterGuestDateException;

  void cancel(int userId, int bookingId);

  void delete(int id);

  void update(int id, BookingUpdateDto updatedBooking) throws BookingNotFoundException;
}
