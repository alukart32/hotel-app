package ru.relex.hotelteam.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.mapper.IBookingMapper;
import ru.relex.hotelteam.service.IBookingService;
import ru.relex.hotelteam.service.dto.BookingCreateDTO;
import ru.relex.hotelteam.service.dto.BookingDTO;
import ru.relex.hotelteam.service.dto.BookingUpdateDTO;
import ru.relex.hotelteam.service.mapstruct.IBookingMapstruct;

import java.util.List;
import java.util.function.Supplier;

@Service
public class BookingServiceImpl implements IBookingService {

    private final IBookingMapper mapper;
    private final IBookingMapstruct mapstruct;

    public BookingServiceImpl(final IBookingMapper mapper,
                           final IBookingMapstruct mapstruct) {
        this.mapper = mapper;
        this.mapstruct = mapstruct;
    }

    @Override
    public BookingDTO createBooking(BookingCreateDTO booking) {
        return mapstruct.toDTO(mapper.createBooking(mapstruct.fromCreateDTO(booking)));
    }

    @Override
    public BookingDTO findById(int id) {
        return mapstruct.toDTO(mapper.getBookingById(id).orElseThrow());
    }

    @Override
    public List<BookingDTO> listBookings() {
        return mapstruct.toDTO(mapper.listBookings());
    }

    @Override
    public List<BookingDTO> listBookingsByUserId(int userId) {
        return mapstruct.toDTO(mapper.listBookingsByUserId(userId));
    }

    @Override
    public void delete(int id) {
        mapper.deleteBooking(id);
    }

    @Override
    public void update(int id, BookingUpdateDTO updatedBooking) {

        Booking booking = mapper.getBookingById(id)
                .orElseThrow(notFound("No booking [ id = " + id + " ] was found!"));

        booking.setId(id);
        booking.setCheckInDate(updatedBooking.getCheckInDate());
        booking.setCheckOutDate(updatedBooking.getCheckOutDate());
        //booking.setRoom(updatedBooking.getRoom())

        mapper.updateBooking(booking);
    }

    private Supplier<RuntimeException> notFound(String s) {
        return () -> new RuntimeException(s);
    }
}