package com.management.hrr.mapper;

import com.management.hrr.dto.reservation.CreateReservationDto;
import com.management.hrr.dto.reservation.ReservationDto;
import com.management.hrr.dto.reservation.UpdateReservationDto;
import com.management.hrr.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);
    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "roomNumber", source = "room.roomNumber")
    @Mapping(target = "roomType", source = "room.type")
    ReservationDto reservationToReservationDto(Reservation reservation);
    List<ReservationDto> reservationsToReservationDtos(List<Reservation> reservations);
    @Mapping(target = "room.id", source = "roomId")
    Reservation reservationDtoToReservation(ReservationDto reservationDto);
    @Mapping(target = "room.id", source = "roomId")
    Reservation createReservationDtoToReservation(CreateReservationDto reservationDto);
    @Mapping(target = "room.id", source = "roomId")
    Reservation updateReservationDtoToReservation(UpdateReservationDto reservationDto);
}
