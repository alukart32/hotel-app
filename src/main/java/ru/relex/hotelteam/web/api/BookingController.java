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
import ru.relex.hotelteam.IBookingService;
import ru.relex.hotelteam.dto.bookings.BookingCreateDto;
import ru.relex.hotelteam.dto.bookings.BookingDto;
import ru.relex.hotelteam.dto.bookings.BookingRegisterDto;

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
  public BookingDto createBooking(@RequestBody BookingCreateDto dto) {
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
  public void registerGuest(@RequestBody BookingRegisterDto registerDto) {
    bookingService.registration(registerDto);
  }

  @PutMapping("/eviction/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void checkOut(@PathVariable("id") int id) {
    bookingService.checkOut(id);
  }

  @GetMapping("/{id}")
  public BookingDto findById(@PathVariable("id") int id) {
    return bookingService.findById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void removeBooking(@PathVariable("id") int id) {
    bookingService.delete(id);
  }

  /*

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBooking(@PathVariable("id") int id, @RequestBody BookingUpdateDto dto){
      bookingService.update(id, dto);
    }
  */
  @GetMapping("/active")
  public List<BookingDto> listActiveBookings() {
    return bookingService.listActiveBookings();
  }

  @GetMapping("/active/guests/{id}")
  public List<BookingDto> listActiveBookingsForUser(@PathVariable("id") int userId) {
    return bookingService.listActiveBookingsForUser(userId);
  }


  @PutMapping("/cancel/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void cancelBooking(@PathVariable("id") int bookingId) {
    bookingService.cancel(bookingId);
  }
}
