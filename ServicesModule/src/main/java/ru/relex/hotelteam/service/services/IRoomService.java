package ru.relex.hotelteam.service.services;

import java.util.List;
import ru.relex.hotelteam.service.dto.RoomBaseDto;

/**
 * Author: Yakimov Date: 20.07.2019 Time: 21:04
 */
public interface IRoomService {

  RoomBaseDto getRoomById(int id);

  List<RoomBaseDto> getAllRooms();

  void deleteRoom(int id);

  RoomBaseDto updateRoom(RoomBaseDto room);

  int saveRoom(RoomBaseDto room);

}
