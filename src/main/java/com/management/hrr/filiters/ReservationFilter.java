package com.management.hrr.filiters;

import com.management.hrr.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationFilter {
    private Long roomId;
    private String guestName;
    private String createdBy;
    private Boolean currentReservation;
}
