package com.management.hrr.controller;

import com.management.hrr.dto.room.CreateRoomDto;
import com.management.hrr.dto.room.RoomDto;
import com.management.hrr.dto.room.UpdateRoomDto;
import com.management.hrr.filiters.RoomFilter;
import com.management.hrr.service.implementations.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class RoomController {

    private RoomService service;

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@Valid @RequestBody CreateRoomDto room) {
        RoomDto savedRoom = service.create(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable("id") long Id) {
        RoomDto room = service.getById(Id);
        return ResponseEntity.ok(room);
    }

    @PutMapping("{id}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable("id") long Id, @Valid @RequestBody UpdateRoomDto room) {
        RoomDto updated = service.update(Id, room);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") long Id) {
        service.delete(Id);
        return ResponseEntity.ok("deleted Successfully!");
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getRooms(@Valid RoomFilter filter) {
        List<RoomDto> rooms = service.getAll(filter);
        return ResponseEntity.ok(rooms);
    }
}
