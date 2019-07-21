package ru.relex.hotelteam.service.mapstruct;

import org.mapstruct.Mapper;
import ru.relex.hotelteam.db.domain.Room;
import ru.relex.hotelteam.service.dto.RoomBaseDto;

@Mapper
public interface IRoomMapstruct {

  RoomBaseDto toDto(Room room);

  Room toDomain(RoomBaseDto room);

}
