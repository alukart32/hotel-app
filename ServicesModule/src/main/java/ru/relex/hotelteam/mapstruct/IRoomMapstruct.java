package ru.relex.hotelteam.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.relex.hotelteam.db.domain.Room;
import ru.relex.hotelteam.dto.RoomBaseDto;
import ru.relex.hotelteam.dto.RoomWithIdDto;

@Mapper(componentModel = "spring", uses = IFacilityMapstruct.class)
public interface IRoomMapstruct {

  @Mapping(target = "type", ignore = true)
  @Mapping(target = "category", ignore = true)
  @Mapping(source = "typeId", target = "type.id")
  @Mapping(source = "places", target = "type.places")
  @Mapping(source = "twinBed", target = "type.twinbed")
  @Mapping(source = "categoryId", target = "category.id")
  @Mapping(source = "categoryName", target = "category.name")
  RoomBaseDto toBaseDto(Room room);

  @Mapping(target = "type", ignore = true)
  @Mapping(target = "category", ignore = true)
  @Mapping(source = "typeId", target = "type.id")
  @Mapping(source = "places", target = "type.places")
  @Mapping(source = "twinBed", target = "type.twinbed")
  @Mapping(source = "categoryId", target = "category.id")
  @Mapping(source = "categoryName", target = "category.name")
  RoomWithIdDto toRoomWithIdDto(Room room);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "typeId", source = "type.id")
  @Mapping(target = "places", source = "type.places")
  @Mapping(target = "twinBed", source = "type.twinbed")
  @Mapping(target = "categoryId", source = "category.id")
  @Mapping(target = "categoryName", source = "category.name")
  Room toDomain(RoomBaseDto dto);

  @Mapping(target = "typeId", source = "type.id")
  @Mapping(target = "places", source = "type.places")
  @Mapping(target = "twinBed", source = "type.twinbed")
  @Mapping(target = "categoryId", source = "category.id")
  @Mapping(target = "categoryName", source = "category.name")
  Room toDomain(RoomWithIdDto dto);

}
