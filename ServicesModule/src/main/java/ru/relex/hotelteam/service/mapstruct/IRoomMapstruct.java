package ru.relex.hotelteam.service.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.relex.hotelteam.db.domain.Room;
import ru.relex.hotelteam.service.dto.RoomBaseDto;
import ru.relex.hotelteam.service.dto.RoomWithIdDto;

@Mapper(componentModel = "spring")
public interface IRoomMapstruct {

  @Mapping(source = "facilities", target = "facilities",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
  RoomBaseDto toBaseDto(Room room);

  RoomWithIdDto toRoomWithIdDto(Room room);

  @Mapping(target = "id", ignore = true)
  Room toDomain(RoomBaseDto dto);

  Room toDomain(RoomWithIdDto dto);

}
