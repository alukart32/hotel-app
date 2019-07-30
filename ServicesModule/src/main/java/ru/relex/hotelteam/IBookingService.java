package ru.relex.hotelteam;

import java.util.List;
import ru.relex.hotelteam.dto.bookings.BookingCreateDto;
import ru.relex.hotelteam.dto.bookings.BookingDto;
import ru.relex.hotelteam.dto.bookings.BookingFullDto;
import ru.relex.hotelteam.dto.bookings.BookingRegisterDto;
import ru.relex.hotelteam.exceptions.CreateBookingException;
import ru.relex.hotelteam.exceptions.EntityNotFoundException;
import ru.relex.hotelteam.exceptions.RegisterGuestException;

public interface IBookingService {

  BookingDto createBooking(BookingCreateDto booking) throws CreateBookingException;

  BookingDto findById(int id) throws EntityNotFoundException;

  List<BookingDto> listBookings();

  List<BookingDto> listBookingsByUserId(int userId);

  List<BookingDto> listActiveBookings();

  List<BookingDto> listActiveBookingsForUser(int userId);

  List<BookingFullDto> getBookingHistoryForUser(int userId);

  void registration(BookingRegisterDto registerDto)
      throws RegisterGuestException, EntityNotFoundException;

  void checkOut(int id)
      throws EntityNotFoundException;

  void cancel(int id);

  void delete(int id);

}
