package com.management.hrr.dto.reservation;

import com.management.hrr.validators.annotations.CompareDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@CompareDate(before = "startDate", after="endDate", message = "The start date must be lower than the end date")
public class BaseReservationDto {
    @NotNull(message = "This is a required field.")
    private Long roomId;
    @NotNull(message = "This is a required field.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Start Date must be future or present.")
    private LocalDate startDate;
    @NotNull(message = "This is a required field.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "End Date must be future or present.")
    private LocalDate endDate;
    @NotNull(message = "Guest Name is a required field")
    @NotBlank(message = "Guest Name is a required field")
    private String guestName;
    @NotNull(message = "Total Price is a required field")
    @Min(value = 1, message = "Total Price must be greater than or equal to 1")
    private BigDecimal totalPrice;
    @NotNull(message = "Created By is a required field")
    @NotBlank(message = "Created By is a required field")
    private String createdBy;
}
