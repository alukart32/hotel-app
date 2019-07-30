package ru.relex.hotelteam.mapstruct;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.domain.BookingFull;
import ru.relex.hotelteam.dto.bookings.BookingCreateDto;
import ru.relex.hotelteam.dto.bookings.BookingDto;
import ru.relex.hotelteam.dto.bookings.BookingFullDto;
import ru.relex.hotelteam.dto.bookings.BookingUpdateDto;

@Mapper(componentModel = "spring",
    uses = {
        IRoomMapstruct.class,
        IBookingPaymentMapstruct.class
    })
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

  @Mapping(target = "room.id", source = "roomId")
  @Mapping(target = "room.category.id", source = "categoryId")
  @Mapping(target = "room.category.name", source = "categoryName")
  @Mapping(target = "room.number", source = "number")
  @Mapping(target = "room.floor", source = "floor")
  @Mapping(target = "room.type.id", source = "typeId")
  @Mapping(target = "room.type.places", source = "places")
  @Mapping(target = "room.type.twinbed", source = "twinBed")
  @Mapping(target = "room.dailyPrice", source = "dailyPrice")
  @Mapping(target = "room.facilities", source = "facilities")
  @Mapping(target = "booking.id", source = "bookingId")
  @Mapping(target = "booking.userId", source = "userId")
  @Mapping(target = "booking.roomId", source = "roomId")
  @Mapping(target = "booking.checkInDate", source = "checkInDate")
  @Mapping(target = "booking.checkOutDate", source = "checkOutDate")
  @Mapping(target = "booking.realCheckInDate", source = "realCheckInDate")
  @Mapping(target = "booking.realCheckOutDate", source = "realCheckOutDate")
  @Mapping(target = "payment.bookingId", source = "bookingId")
  @Mapping(target = "payment.date", source = "date")
  @Mapping(target = "payment.total", source = "total")
  BookingFullDto toFullDto(BookingFull bookingFull);

  List<BookingFullDto> toFullDto(List<BookingFull> bookingFullList);
}
