package ru.relex.hotelteam.service.mapstruct;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.dto.bookings.BookingCreateDto;
import ru.relex.hotelteam.dto.bookings.BookingDto;
import ru.relex.hotelteam.dto.bookings.BookingUpdateDto;

@Mapper(componentModel = "spring")
public interface IBookingMapstruct {

  Booking fromDto(BookingDto dto);

  BookingDto toDto(Booking booking);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "realCheckInDate", ignore = true)
  @Mapping(target = "realCheckOutDate", ignore = true)
  Booking fromCreateDto(BookingCreateDto dto);

  BookingCreateDto toCreateDto(Booking booking);

  @Mapping(target = "id", ignore = true)
  Booking fromUpdateDto(BookingUpdateDto dto);

  BookingUpdateDto toUpdateDto(Booking booking);

  List<BookingDto> toDto(List<Booking> bookings);

  List<Booking> fromDto(List<BookingDto> bookingDtoList);
}
