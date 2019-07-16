package ru.relex.hotelteam.service;

import ru.relex.hotelteam.service.dto.domain.BookingCreateDTO;
import ru.relex.hotelteam.service.dto.domain.BookingDTO;
import ru.relex.hotelteam.service.dto.domain.BookingUpdateDTO;

import java.util.List;

public interface IBookingService {

    BookingDTO createBooking (BookingCreateDTO booking);

    BookingDTO findById(int id);

    List<BookingDTO> listBookings();

    List<BookingDTO> listBookingsByUserId(int userId);

    void delete(int id);

    void update(int id, BookingUpdateDTO updatedBooking);
}