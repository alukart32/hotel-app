package ru.relex.hotelteam.service.mapstruct;

import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.service.dto.domain.BookingCreateDTO;
import ru.relex.hotelteam.service.dto.domain.BookingDTO;
import ru.relex.hotelteam.service.dto.domain.BookingUpdateDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBookingMapstruct {

        Booking fromBookingCreateDTO(BookingCreateDTO dto);

        BookingCreateDTO toBookingCreateDTO(Booking booking);

        Booking fromBookingUpdateDTO(BookingUpdateDTO dto);

        BookingUpdateDTO toBookingUpdateDTO(Booking booking);

        List<BookingDTO> toDTO(List<Booking> bookings);

        List<Booking> fromDTO(List<BookingDTO> bookingDTOs);
}
