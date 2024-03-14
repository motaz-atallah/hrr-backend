package com.management.hrr.mapper;

import com.management.hrr.dto.room.CreateRoomDto;
import com.management.hrr.dto.room.RoomDto;
import com.management.hrr.dto.room.UpdateRoomDto;
import com.management.hrr.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ReservationMapper.class)
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
    RoomDto roomToRoomDTO(Room room);
    List<RoomDto> roomsToRoomDtos(List<Room> rooms);
    Room roomDTOToRoom(RoomDto roomDTO);
    Room createRoomDTOToRoom(CreateRoomDto roomDTO);
    Room updateRoomDTOToRoom(UpdateRoomDto roomDTO);
}
