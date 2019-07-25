package ru.relex.hotelteam.service.mapstruct;

import java.util.List;
import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.service.dto.BookingCreateDto;
import ru.relex.hotelteam.service.dto.BookingDto;
import ru.relex.hotelteam.service.dto.BookingUpdateDto;

@Mapper(componentModel = "spring")
public interface IBookingMapstruct {

  Booking fromDto(BookingDto dto);

  BookingDto toDto(Booking booking);

  Booking fromCreateDto(BookingCreateDto dto);

  BookingCreateDto toCreateDto(Booking booking);

  Booking fromUpdateDto(BookingUpdateDto dto);

  BookingUpdateDto toUpdateDto(Booking booking);

  List<BookingDto> toDto(List<Booking> bookings);

  List<Booking> fromDto(List<BookingDto> bookingDtoList);
}
