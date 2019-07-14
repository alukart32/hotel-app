package ru.relex.hotelteam.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.mapper.IBookingMapper;
import ru.relex.hotelteam.service.IBookingService;
import ru.relex.hotelteam.service.dto.domain.BookingCreateDTO;
import ru.relex.hotelteam.service.dto.domain.BookingDTO;
import ru.relex.hotelteam.service.dto.domain.BookingUpdateDTO;
import ru.relex.hotelteam.service.mapstruct.IBookingMapstruct;

import java.util.List;

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
    public void delete(int id) {
        mapper.deleteBooking(id);
    }

    @Override
    public BookingUpdateDTO update(int id, BookingUpdateDTO updatedBooking) {

        Booking booking = mapper.getBookingById(id).orElseThrow();

        /**
         * просто без проверок
         */
        booking.setId(id);
        booking.setCheckInDate(updatedBooking.getCheckInDate());
        booking.setCheckOutDate(updatedBooking.getCheckOutDate());
        //booking.setRoom(updatedBooking.getRoom())

        mapper.updateBooking(booking);
        // то ли тот сохранённый объект возвращаем, то ли изменённый объект на сохранение, чтобы
        //  чтобы не обращаться лишний раз к бд ?
        return mapstruct.toUpdateDTO(booking);
    }
}
