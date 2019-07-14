package ru.relex.hotelteam.service.impl;

import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.service.IBookingService;
import ru.relex.hotelteam.service.dto.domain.BookingCreateDTO;
import ru.relex.hotelteam.service.dto.domain.BookingDTO;
import ru.relex.hotelteam.service.dto.domain.BookingUpdateDTO;
import ru.relex.hotelteam.service.mapstruct.IUserMapstruct;

import java.util.List;

public class BookingServiceImpl implements IBookingService {

    private final IUserMapper mapper;
    private final IUserMapstruct mapstruct;

    public BookingServiceImpl(final IUserMapper mapper,
                           final IUserMapstruct mapstruct) {
        this.mapper = mapper;
        this.mapstruct = mapstruct;
    }

    @Override
    public BookingDTO createBooking(BookingCreateDTO booking) {
        return null;
    }

    @Override
    public BookingDTO findById(int id) {
        return null;
    }

    @Override
    public List<BookingDTO> listBookings() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public BookingUpdateDTO update(int id, BookingUpdateDTO updatedBooking) {
        return null;
    }
}
