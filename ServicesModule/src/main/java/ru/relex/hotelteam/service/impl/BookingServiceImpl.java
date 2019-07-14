package ru.relex.hotelteam.service.impl;

import ru.relex.hotelteam.db.mapper.IBookingMapper;
import ru.relex.hotelteam.service.IBookingService;
import ru.relex.hotelteam.service.dto.domain.BookingCreateDTO;
import ru.relex.hotelteam.service.dto.domain.BookingDTO;
import ru.relex.hotelteam.service.dto.domain.BookingUpdateDTO;
import ru.relex.hotelteam.service.mapstruct.IBookingMapstruct;

import java.util.List;

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

    }

    @Override
    public BookingUpdateDTO update(int id, BookingUpdateDTO updatedBooking) {
        return null;
    }
}
