package ru.relex.hotelteam;

import java.sql.SQLException;
import java.util.List;
import ru.relex.hotelteam.dto.RoomBaseDto;
import ru.relex.hotelteam.dto.RoomWithIdDto;
import ru.relex.hotelteam.dto.bookings.BookingIntervalDto;
import ru.relex.hotelteam.exceptions.EntityNotFoundException;

/**
 * Author: Yakimov Date: 20.07.2019 Time: 21:04
 */
public interface IRoomService {

  RoomWithIdDto getRoomById(int id) throws EntityNotFoundException;

  List<RoomWithIdDto> getAllRooms();

  List<RoomWithIdDto> getVacancies(BookingIntervalDto dto);

  void deleteRoom(int id);

  RoomWithIdDto updateRoom(int id, RoomBaseDto room) throws EntityNotFoundException;

  RoomWithIdDto saveRoom(RoomBaseDto room) throws SQLException;

}
