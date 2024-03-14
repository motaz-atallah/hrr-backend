package com.management.hrr.dto.reservation;

import com.management.hrr.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto extends BaseReservationDto {
    private Long id;
    private String roomNumber;
    private RoomType roomType;
}
