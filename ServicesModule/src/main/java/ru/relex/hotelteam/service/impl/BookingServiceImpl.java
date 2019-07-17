package ru.relex.hotelteam.service.impl;

import java.util.List;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.mapper.IBookingMapper;
import ru.relex.hotelteam.service.IBookingService;
import ru.relex.hotelteam.service.dto.BookingCreateDto;
import ru.relex.hotelteam.service.dto.BookingDto;
import ru.relex.hotelteam.service.dto.BookingUpdateDto;
import ru.relex.hotelteam.service.mapstruct.IBookingMapstruct;

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
  public BookingDto createBooking(BookingCreateDto booking) {
    return mapstruct.toDto(mapper.createBooking(mapstruct.fromCreateDto(booking)));
  }

  @Override
  public BookingDto findById(int id) {
    return mapstruct.toDto(mapper.getBookingById(id).orElseThrow());
  }

  @Override
  public List<BookingDto> listBookings() {
    return mapstruct.toDto(mapper.listBookings());
  }

  @Override
  public List<BookingDto> listBookingsByUserId(int userId) {
    return mapstruct.toDto(mapper.listBookingsByUserId(userId));
  }

  @Override
  public void delete(int id) {
    mapper.deleteBooking(id);
  }

  @Override
  public void update(int id, BookingUpdateDto updatedBooking) {

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
