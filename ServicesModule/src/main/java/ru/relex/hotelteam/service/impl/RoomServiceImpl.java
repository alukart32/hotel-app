package ru.relex.hotelteam.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.db.domain.Room;
import ru.relex.hotelteam.db.mapper.IRoomMapper;
import ru.relex.hotelteam.exceptions.EntityNotFoundException;
import ru.relex.hotelteam.service.dto.RoomBaseDto;
import ru.relex.hotelteam.service.dto.RoomWithIdDto;
import ru.relex.hotelteam.service.mapstruct.IRoomMapstruct;
import ru.relex.hotelteam.service.services.IRoomService;

@Service
public class RoomServiceImpl implements IRoomService {

  private IRoomMapper mapper;

  private IRoomMapstruct mapstruct;

  public RoomServiceImpl(IRoomMapper mapper, IRoomMapstruct mapstruct) {
    this.mapper = mapper;
    this.mapstruct = mapstruct;
  }

  @Override
  public RoomWithIdDto getRoomById(int id) {
    Optional<Room> domain = mapper.getRoomById(id);
    return domain.map(room -> mapstruct.toRoomWithIdDto(room))
        .orElseThrow(() -> new EntityNotFoundException("Room", id));
  }

  @Override
  public List<RoomWithIdDto> getAllRooms() {
    var rooms = mapper.getAllRooms().stream().map(room -> mapstruct.toRoomWithIdDto(room)).collect(Collectors.toList());
    rooms.forEach(room -> room.setFacilities(mapper.getFacilitiesForRoom(room.getId())));
    return rooms;
  }

  @Override
  public void deleteRoom(int id) {
    mapper.deleteRoom(id);
  }

  @Override
  public RoomWithIdDto updateRoom(int id, RoomBaseDto room) {
    Room domain = mapstruct.toDomain(room);
    domain = mapper.updateRoom(id, domain).orElseThrow(() -> new EntityNotFoundException("Room", id));
    return mapstruct.toRoomWithIdDto(domain);
  }

  @Override
  public RoomWithIdDto saveRoom(RoomBaseDto room) throws SQLException {
    Room domain = mapstruct.toDomain(room);
    int id = mapper.saveRoom(domain);
    mapper.saveFacilitiesForRoom(id, domain.getFacilities());
    Optional<RoomWithIdDto> saved = mapper.getRoomById(id)
        .map(savedDomain -> mapstruct.toRoomWithIdDto(savedDomain));
    return saved.orElseThrow(() -> new SQLException("RoomServiceImpls: Error while saving Room"));
  }
}
