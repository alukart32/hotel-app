package ru.relex.hotelteam.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.mapper.IBookingMapper;
import ru.relex.hotelteam.service.IBookingService;
import ru.relex.hotelteam.service.dto.BookingCreateDto;
import ru.relex.hotelteam.service.dto.BookingDto;
import ru.relex.hotelteam.service.dto.BookingRegisterDto;
import ru.relex.hotelteam.service.dto.BookingUpdateDto;
import ru.relex.hotelteam.service.mapstruct.IBookingMapstruct;

@Service
public class BookingServiceImpl implements IBookingService {

  private final IBookingMapper mapper;
  private final IBookingMapstruct mapstruct;
  private final BookingPaymentServiceImpl paymentService;

  public BookingServiceImpl(IBookingMapper mapper, IBookingMapstruct mapstruct,
      BookingPaymentServiceImpl paymentService) {
    this.mapper = mapper;
    this.mapstruct = mapstruct;
    this.paymentService = paymentService;
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
  public List<BookingDto> listBookingsByRoomId(int roomId) {
    return mapstruct.toDto(mapper.listBookingsByRoomId(roomId));
  }

  /**
   * Register a guest means that it needs to update a realCheckInDate field and create payment
   *
   * @param registerDto the guest, date when he has checked in
   */
  @Override
  public void registerGuest(BookingRegisterDto registerDto) {

    Booking currentBooking = mapstruct.fromDto(findBookingForCheckIn(registerDto));

    currentBooking.setRealCheckInDate(registerDto.getCheckIn());
    currentBooking.setUserId(registerDto.getUserId());

    update(currentBooking.getId(), mapstruct.toUpdateDto(currentBooking));

    paymentService.createPayment(currentBooking);
  }

  @Override
  public void delete(int id) {
    paymentService.deletePaymentByBookingId(id);
    mapper.deleteBooking(id);
  }

  @Override
  public void update(int id, BookingUpdateDto updatedBooking) {

    Booking booking = mapper.getBookingById(id)
        .orElseThrow(notFound("No booking [ id = " + id + " ] was found!"));

    booking.setRoomId(updatedBooking.getRoomId());
    booking.setUserId(updatedBooking.getUserId());
    booking.setCheckInDate(updatedBooking.getCheckInDate());
    booking.setCheckOutDate(updatedBooking.getCheckOutDate());
    booking.setRealCheckInDate(updatedBooking.getRealCheckInDate());
    booking.setRealCheckOutDate(updatedBooking.getRealCheckOutDate());

    mapper.updateBooking(booking);
  }

  private BookingDto findBookingForCheckIn(BookingRegisterDto registerDto) {
    List<BookingDto> bookingList = listBookingsByUserId(registerDto.getUserId());

    bookingList.removeIf(b -> b.getRealCheckOutDate() != null);

    LocalDateTime registerDate = registerDto.getCheckIn();

    final BookingDto[] currentBooking = new BookingDto[1];

    bookingList.forEach(b -> {
      if (registerDate.isAfter(b.getCheckInDate())
          & registerDate.isBefore(b.getCheckOutDate())) {
        currentBooking[0] = b;
      }
    });

    return currentBooking[0];
  }

  private Supplier<RuntimeException> notFound(String s) {
    return () -> new RuntimeException(s);
  }
}
