package ru.relex.hotelteam.web.api;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.relex.hotelteam.service.IBookingService;
import ru.relex.hotelteam.service.dto.BookingCheckOutDto;
import ru.relex.hotelteam.service.dto.BookingCreateDto;
import ru.relex.hotelteam.service.dto.BookingDto;
import ru.relex.hotelteam.service.dto.BookingRegisterDto;
import ru.relex.hotelteam.service.dto.BookingUpdateDto;
import ru.relex.hotelteam.shared.exception.service.BookingNotFoundException;
import ru.relex.hotelteam.shared.exception.service.CreateBookingException;
import ru.relex.hotelteam.shared.exception.service.RegisterGuestDateException;
import ru.relex.hotelteam.shared.exception.service.UserNotFoundException;

@RestController
@RequestMapping(path = "/bookings",
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BookingController {

  private final IBookingService bookingService;

  public BookingController(IBookingService bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookingDto createBooking(@RequestBody BookingCreateDto dto) throws CreateBookingException {
    return bookingService.createBooking(dto);
  }

  @GetMapping
  public List<BookingDto> listBookings() {
    return bookingService.listBookings();
  }

  @GetMapping("/users/{userId}")
  public List<BookingDto> listBookingsByUserId(@PathVariable("userId") int userId) {
    return bookingService.listBookingsByUserId(userId);
  }

  @PostMapping("/users/registration")
  @ResponseStatus(HttpStatus.OK)
  public void registerGuest(@RequestBody BookingRegisterDto registerDto){
    bookingService.registration(registerDto);
  }

  @PutMapping("/{id}/users/leave")
  @ResponseStatus(HttpStatus.OK)
  public void checkOutGuest(@RequestBody BookingCheckOutDto checkOutDto){
    bookingService.checkOutGuest(checkOutDto);
  }

  @GetMapping("/rooms/{roomId}")
  public List<BookingDto> listBookingsByRoomId(@PathVariable("roomId") int roomId) {
    return bookingService.listBookingsByRoomId(roomId);
  }

  @GetMapping("/{id}")
  public BookingDto findById(@PathVariable("id") int id)
    throws BookingNotFoundException {
    return bookingService.findById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void removeBooking(@PathVariable("id") int id) {
    bookingService.delete(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updateBooking(@PathVariable("id") int id, @RequestBody BookingUpdateDto dto)
    throws UserNotFoundException, BookingNotFoundException {
    bookingService.update(id, dto);
  }

  @PutMapping("/users/{userId}/cancel/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void cancelBooking(@PathVariable("userId") int userId, @PathVariable("id") int bookingId){
    bookingService.cancel(userId, bookingId);
  }
}
