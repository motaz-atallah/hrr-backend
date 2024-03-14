package com.management.hrr.validators;

import com.management.hrr.validators.annotations.CompareDate;

import java.lang.reflect.Field;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompareDateValidator implements ConstraintValidator <CompareDate, Object> {

    private String beforeFieldName;
    private String afterFieldName;

    @Override
    public void initialize(final CompareDate constraintAnnotation) {
        beforeFieldName = constraintAnnotation.before();
        afterFieldName = constraintAnnotation.after();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Field beforeDateField = value.getClass().getDeclaredField(beforeFieldName);
            beforeDateField.setAccessible(true);

            final Field afterDateField = value.getClass().getDeclaredField(afterFieldName);
            afterDateField.setAccessible(true);

            final Date beforeDate = (Date) beforeDateField.get(value);
            final Date afterDate = (Date) afterDateField.get(value);

            return beforeDate == null && afterDate == null || beforeDate != null && beforeDate.before(afterDate);
        } catch (final Exception e) {
            e.printStackTrace();

            return false;
        }
    }
}
//
//import com.management.hrr.dto.reservation.BaseReservationDto;
//import com.management.hrr.validators.annotations.ValidReservationDates;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeParseException;
//
//public class ReservationDatesValidator implements ConstraintValidator<ValidReservationDates, BaseReservationDto> {
//
//    @Override
//    public void initialize(ValidReservationDates constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(BaseReservationDto reservationDto, ConstraintValidatorContext context) {
//        if (reservationDto == null) {
//            return true; // null values are handled by @NotNull annotation
//        }
//
//        LocalDateTime today = LocalDateTime.now();
//        LocalDateTime startDate = reservationDto.getStartDate();
//        LocalDateTime endDate = reservationDto.getEndDate();
//
//        // Check if start date and end date are not null and represent valid dates
//        return startDate != null && endDate != null &&
//                isValidDate(startDate) && isValidDate(endDate) &&
//                startDate.isAfter(today) && startDate.isBefore(endDate);
//    }
//
//    // Helper method to check if a date is valid
//    private boolean isValidDate(LocalDateTime date) {
//        try {
//            // Try to parse the date
//            LocalDateTime.parse(date.toString());
//            return true;
//        } catch (DateTimeParseException e) {
//            // Parsing failed, date is not valid
//            return false;
//        }
//    }
//}
