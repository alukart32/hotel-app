package ru.relex.hotelteam.service.mapstruct;

import java.util.List;
import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.BookingPayment;
import ru.relex.hotelteam.service.dto.BookingPaymentDto;

@Mapper(componentModel = "spring")
public interface IBookingPaymentMapstruct {

  BookingPaymentDto toDto(BookingPayment payment);

  BookingPayment fromDto(BookingPaymentDto dto);

  List<BookingPaymentDto> toDto(List<BookingPayment> payments);

  List<BookingPayment> fromDto(List<BookingPaymentDto> dto);
}
