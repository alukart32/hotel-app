package ru.relex.hotelteam.service.services;

import java.sql.SQLException;
import java.util.List;
import ru.relex.hotelteam.exceptions.EntityNotFoundException;
import ru.relex.hotelteam.service.dto.RoomBaseDto;
import ru.relex.hotelteam.service.dto.RoomWithIdDto;

/**
 * Author: Yakimov Date: 20.07.2019 Time: 21:04
 */
public interface IRoomService {

  RoomWithIdDto getRoomById(int id) throws EntityNotFoundException;

  List<RoomWithIdDto> getAllRooms();

  void deleteRoom(int id);

  RoomWithIdDto updateRoom(int id, RoomBaseDto room) throws EntityNotFoundException;

  RoomWithIdDto saveRoom(RoomBaseDto room) throws SQLException;

}
