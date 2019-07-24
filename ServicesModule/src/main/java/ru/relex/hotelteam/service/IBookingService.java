package ru.relex.hotelteam.service;

import java.util.List;
import ru.relex.hotelteam.service.dto.BookingCreateDto;
import ru.relex.hotelteam.service.dto.BookingDto;
import ru.relex.hotelteam.service.dto.BookingRegisterDto;
import ru.relex.hotelteam.shared.exception.service.BookingNotFoundException;
import ru.relex.hotelteam.shared.exception.service.CreateBookingException;
import ru.relex.hotelteam.shared.exception.service.RegisterGuestDateException;
import ru.relex.hotelteam.shared.exception.service.UserNotFoundException;

public interface IBookingService {

  BookingDto createBooking(BookingCreateDto booking) throws CreateBookingException;

  BookingDto findById(int id) throws BookingNotFoundException;

  List<BookingDto> listBookings();

  List<BookingDto> listBookingsByUserId(int userId);

  void registration(BookingRegisterDto registerDto)
      throws RegisterGuestDateException, BookingNotFoundException;

  void checkOut(int bookingId)
      throws UserNotFoundException, BookingNotFoundException;

  void cancel(int bookingId);

  void delete(int id);
}
