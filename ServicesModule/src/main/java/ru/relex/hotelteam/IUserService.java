package ru.relex.hotelteam;

import java.util.List;
import ru.relex.hotelteam.dto.UserBaseDto;
import ru.relex.hotelteam.dto.UserDto;
import ru.relex.hotelteam.dto.UserSecurityDto;
import ru.relex.hotelteam.dto.UserUpdateDto;
import ru.relex.hotelteam.dto.bookings.BookingFullDto;
import ru.relex.hotelteam.exceptions.EntityNotFoundException;

public interface IUserService {

  UserBaseDto createUser(UserDto user);

  UserBaseDto findById(int id) throws EntityNotFoundException;

  List<UserBaseDto> listUsers();

  void delete(int id);

  void update(int id, UserUpdateDto updatedUser);

  void updateSecurityInfo(int id, UserSecurityDto updatedSecurityInfo);

  List<UserBaseDto> getCurrentGuests();

  List<BookingFullDto> getBookingHistoryForUser(int id);
}
