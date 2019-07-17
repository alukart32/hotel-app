package ru.relex.hotelteam.service.mapstruct;

import java.util.List;
import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.service.dto.BookingCreateDTO;
import ru.relex.hotelteam.service.dto.BookingDTO;
import ru.relex.hotelteam.service.dto.BookingUpdateDTO;

@Mapper(componentModel = "spring")
public interface IBookingMapstruct {

  Booking fromDTO(BookingDTO dto);

  BookingDTO toDTO(Booking booking);

  Booking fromCreateDTO(BookingCreateDTO dto);

  BookingCreateDTO toCreateDTO(Booking booking);

  Booking fromUpdateDTO(BookingUpdateDTO dto);

  BookingUpdateDTO toUpdateDTO(Booking booking);

  List<BookingDTO> toDTO(List<Booking> bookings);

  List<Booking> fromDTO(List<BookingDTO> bookingDTOs);
}
