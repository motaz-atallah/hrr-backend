package com.management.hrr.controller;

import com.management.hrr.dto.reservation.CreateReservationDto;
import com.management.hrr.dto.reservation.ReservationDto;
import com.management.hrr.dto.reservation.UpdateReservationDto;
import com.management.hrr.filiters.ReservationFilter;
import com.management.hrr.service.implementations.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Validated
public class ReservationController {

    private ReservationService service;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody CreateReservationDto reservation) {
        ReservationDto savedReservation = service.create(reservation);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") long Id) {
        ReservationDto room = service.getById(Id);
        return ResponseEntity.ok(room);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable("id") long Id, @Valid @RequestBody UpdateReservationDto room) {
        ReservationDto updated = service.update(Id, room);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") long Id) {
        service.delete(Id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getReservations(@Valid ReservationFilter filter) {
        List<ReservationDto> rooms = service.getAll(filter);
        return ResponseEntity.ok(rooms);
    }
}
