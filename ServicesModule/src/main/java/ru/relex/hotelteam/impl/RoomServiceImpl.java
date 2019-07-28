package ru.relex.hotelteam.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.relex.hotelteam.IRoomService;
import ru.relex.hotelteam.db.domain.Room;
import ru.relex.hotelteam.db.mapper.IRoomMapper;
import ru.relex.hotelteam.dto.RoomBaseDto;
import ru.relex.hotelteam.dto.RoomWithIdDto;
import ru.relex.hotelteam.dto.bookings.BookingIntervalDto;
import ru.relex.hotelteam.exceptions.EntityNotFoundException;
import ru.relex.hotelteam.mapstruct.IRoomMapstruct;
import ru.relex.hotelteam.shared.model.CurrentUser;

@Service
public class RoomServiceImpl implements IRoomService {

  private final IRoomMapper mapper;

  private final IRoomMapstruct mapstruct;


  /*
    Spring создаёт прокси-маску для нашего объекта CurrentUser.
    Этот объект "лениво" инициализируется: т.е. он не будет создан,
    пока мы не вызовем хотя бы один его метод.
   */
  private final CurrentUser currentUser;

  public RoomServiceImpl(IRoomMapper mapper, IRoomMapstruct mapstruct,
      CurrentUser currentUser) {
    this.mapper = mapper;
    this.mapstruct = mapstruct;
    this.currentUser = currentUser;
  }

  @Override
  public RoomWithIdDto getRoomById(int id) {
    Optional<Room> domain = mapper.getRoomById(id);
    return domain.map(mapstruct::toRoomWithIdDto)
        .orElseThrow(() -> new EntityNotFoundException("Room", id));
  }

  @Override
  public List<RoomWithIdDto> getAllRooms() {
    /*
      Если раскомментировать эту строчку, остановиться на ней дебагом и шагнуть внутрь [Step Into(F7)]
      то ты попадёшь в метод создания этого бина. Т.е. инициализация происходит только в момент вызова
     */
    //System.out.println(currentUser.getLogin());
    var rooms = mapper.getAllRooms();
    rooms.forEach(room -> room.setFacilities(mapper.getFacilitiesForRoom(room.getId())));
    return rooms.stream().map(mapstruct::toRoomWithIdDto).collect(Collectors.toList());
  }

  @Override
  public List<RoomWithIdDto> getVacancies(BookingIntervalDto dto) {
    var rooms = mapper.getVacancies(dto.getCheckInDate(), dto.getCheckOutDate());
    rooms.forEach(room -> room.setFacilities(mapper.getFacilitiesForRoom(room.getId())));
    return rooms.stream().map(room -> mapstruct.toRoomWithIdDto(room)).collect(Collectors.toList());
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
        .map(mapstruct::toRoomWithIdDto);
    return saved.orElseThrow(() -> new SQLException("RoomServiceImpls: Error while saving Room"));
  }
}
