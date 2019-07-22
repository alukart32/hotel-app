package ru.relex.hotelteam.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.Booking;
import ru.relex.hotelteam.db.mapper.IBookingMapper;
import ru.relex.hotelteam.service.IBookingService;
import ru.relex.hotelteam.service.dto.BookingCreateDto;
import ru.relex.hotelteam.service.dto.BookingDto;
import ru.relex.hotelteam.service.dto.BookingPaymentDto;
import ru.relex.hotelteam.service.dto.BookingRegisterDto;
import ru.relex.hotelteam.service.dto.BookingUpdateDto;
import ru.relex.hotelteam.service.mapstruct.IBookingMapstruct;
import ru.relex.hotelteam.shared.exception.service.CreateBookingException;
import ru.relex.hotelteam.shared.exception.service.RegisterGuestDateException;

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
  public BookingDto createBooking(BookingCreateDto booking) throws CreateBookingException {

    List<Booking> bookings = mapstruct.fromDto(listBookings());

    final boolean[] hasBooking = {true};

    bookings.removeIf(b -> booking.getRoomId() != b.getRoomId());

    bookings.forEach(b -> {
      if (isInDateInterval(booking, b.getCheckOutDate(), b.getCheckInDate())) {
        hasBooking[0] = false;
      }
    });

    if (hasBooking[0]) {
      return mapstruct.toDto(mapper.createBooking(mapstruct.fromCreateDto(booking)));
    } else {
      throw new CreateBookingException("Room is already booked");
    }
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
  public void registerGuest(BookingRegisterDto registerDto) throws RegisterGuestDateException {

    Booking currentBooking = mapstruct.fromDto(findBookingForCheckIn(registerDto));

    if (currentBooking != null) {
      currentBooking.setRealCheckInDate(registerDto.getCheckIn());
      currentBooking.setUserId(registerDto.getUserId());

      update(currentBooking.getId(), mapstruct.toUpdateDto(currentBooking));

      paymentService.createPayment(currentBooking);
    } else {
      throw new RegisterGuestDateException("Registration date is out of booking dates");
    }
  }

  @Override
  public void cancel(int userId, int bookingId) {
    /**
     * 1) find guest's booking
     * 2) is it paid
     *    2.1) no = delete
     *    2.2) yes = refund?
     */
    List<Booking> bookings = mapstruct.fromDto(listBookingsByUserId(userId));

    Booking booking = null;

    for (Booking b: bookings) {
      if(b.getId() == bookingId) {
        booking = b;
        break;
      }
    }

    if(booking != null){
      BookingPaymentDto payment = paymentService.getPaymentByBookingId(bookingId);

      if(payment == null)
         delete(bookingId);
    }

    // кинем exception

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
          && registerDate.isBefore(b.getCheckOutDate())) {
        currentBooking[0] = b;
      }
    });

    return currentBooking[0];
  }


  /**
   * Checking booking's date. Guest can book a room if it hasn't booked yet.
   *
   * @param bookingCreateDto possible a new booking
   * @param to checkOut date of some booking
   * @param from checkIn date of some booking
   * @return if  dates of a new booking is not between [from, to] dates then returns true
   */
  private boolean isInDateInterval(BookingCreateDto bookingCreateDto,
      LocalDateTime to, LocalDateTime from) {
    // checking that date interval isn't same as a new booking's dates
    if (from.isEqual(bookingCreateDto.getCheckInDate())
        && to.isEqual(bookingCreateDto.getCheckOutDate())) {
      return true;
    }

    // checking that date interval doesn't have a new booking's dates
    if (from.isAfter(bookingCreateDto.getCheckInDate())
        && to.isBefore(bookingCreateDto.getCheckOutDate())) {
      return true;
    }

    // checking that new booking's checkOut date is between [from, to]
    if (bookingCreateDto.getCheckOutDate().isBefore(to)
        && from.isBefore(bookingCreateDto.getCheckOutDate())) {
      return true;
    }

    // checking that new booking's checkInt date is between [from, to]
    if (to.isAfter(bookingCreateDto.getCheckInDate())
        && bookingCreateDto.getCheckInDate().isAfter(from)) {
      return true;
    }

    return false;
  }

  private Supplier<RuntimeException> notFound(String s) {
    return () -> new RuntimeException(s);
  }
}
