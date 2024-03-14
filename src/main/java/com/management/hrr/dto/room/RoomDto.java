package com.management.hrr.dto.room;

import com.management.hrr.dto.reservation.ReservationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto extends BaseRoomDto {
    private long id;
    private List<ReservationDto> reservations;
}
