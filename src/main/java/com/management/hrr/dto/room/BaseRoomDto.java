package com.management.hrr.dto.room;

import com.management.hrr.enums.RoomType;
import com.management.hrr.validators.annotations.EnumValue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseRoomDto {
    @Valid

    @NotNull(message = "Room number is a required field")
    @NotBlank(message = "Room number is a required field")
    private String roomNumber;

    @NotNull(message = "Type is a required field")
    @EnumValue(message = "Invalid room type", enumClass = RoomType.class)
    private RoomType type;

    @NotNull(message = "Price is a required field")
    @Min(value = 1, message = "Price must be greater than or equal to 1")
    private BigDecimal price;

    @NotNull(message = "Amenities are required")
    @NotEmpty(message = "Amenities are required and must not be empty")
    private List<String> amenities;
}
