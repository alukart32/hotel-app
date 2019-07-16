package ru.relex.hotelteam.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.hotelteam.service.IBookingService;
import ru.relex.hotelteam.service.dto.domain.BookingCreateDTO;
import ru.relex.hotelteam.service.dto.domain.BookingDTO;
import ru.relex.hotelteam.service.dto.domain.BookingUpdateDTO;

import java.util.List;

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
    public BookingDTO createBooking(@RequestBody BookingCreateDTO dto) {
        return bookingService.createBooking(dto);
    }

    @GetMapping
    public List<BookingDTO> listBookings() {
        return bookingService.listBookings();
    }

    @GetMapping("/all/{userId}")
    public List<BookingDTO> listBookingsByUserId(@PathVariable("userId") int userId){
        return bookingService.listBookingsByUserId(userId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookingDTO findById(@PathVariable("id") int id) {
        return bookingService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeBooking(@PathVariable("id") int id) {
        bookingService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBooking(@PathVariable("id") int id, @RequestBody BookingUpdateDTO dto){
        bookingService.update(id, dto);
    }
}