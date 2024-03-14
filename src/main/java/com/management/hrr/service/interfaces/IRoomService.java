package com.management.hrr.service.interfaces;

import com.management.hrr.dto.room.CreateRoomDto;
import com.management.hrr.dto.room.RoomDto;
import com.management.hrr.dto.room.UpdateRoomDto;
import com.management.hrr.filiters.RoomFilter;

import java.util.List;

public interface IRoomService {
    RoomDto create(CreateRoomDto room);
    RoomDto update(long id, UpdateRoomDto room);
    RoomDto getById(long id);
    void delete(long id);
    List<RoomDto> getAll(RoomFilter filter);
}
