package ru.relex.hotelteam.mapstruct;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.hotelteam.db.domain.BookingPayment;
import ru.relex.hotelteam.dto.bookings.BookingPaymentDto;

@Mapper(componentModel = "spring")
public interface IBookingPaymentMapstruct {

  BookingPaymentDto toDto(BookingPayment payment);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "paid", ignore = true)
  BookingPayment fromDto(BookingPaymentDto dto);

  List<BookingPaymentDto> toDto(List<BookingPayment> payments);

  List<BookingPayment> fromDto(List<BookingPaymentDto> dto);
}
