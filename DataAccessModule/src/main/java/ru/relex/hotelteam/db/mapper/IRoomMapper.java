package ru.relex.hotelteam.db.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ru.relex.hotelteam.db.domain.Facility;
import ru.relex.hotelteam.db.domain.Room;

/**
 * MyBatis mapper for Room entity Author: Yakimov Date: 17.07.2019 Time: 22:49
 */

@Mapper
public interface IRoomMapper {

  int saveRoom(Room room);

  Optional<Room> getRoomById(@Param("room_id") int id);

  List<Room> getAllRooms();

  void deleteRoom(@Param("id") int id);

  Optional<Room> updateRoom(@Param("room_id") int id, Room room);

  List<Facility> getFacilitiesForRoom(@Param("id") int id);

  void saveFacilitiesForRoom(@Param("id") int id, @Param("facilities") List<Facility> facilities);

  void updateFacilitiesForRoom(@Param("id") int id, @Param("facilities") List<Facility> facilities);
}
