package com.management.hrr.service.interfaces;

import com.management.hrr.dto.reservation.CreateReservationDto;
import com.management.hrr.dto.reservation.ReservationDto;
import com.management.hrr.dto.reservation.UpdateReservationDto;
import com.management.hrr.filiters.ReservationFilter;

import java.util.List;

public interface IReservationService {
    ReservationDto create(CreateReservationDto room);
    ReservationDto update(long id, UpdateReservationDto room);
    ReservationDto getById(long id);
    void delete(long id);
    List<ReservationDto> getAll(ReservationFilter filter);
}
