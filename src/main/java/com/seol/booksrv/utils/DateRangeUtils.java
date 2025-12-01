package com.seol.booksrv.utils;

import com.seol.booksrv.exception.WrongWebUserArgumentException;

import java.time.LocalDate;

public class DateRangeUtils {
    public static void validateDateRangeBasic(LocalDate startDate, LocalDate endDate) {
        validateDateNotEmpty(startDate);
        validateDateNotEmpty(endDate);

        if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
            throw new WrongWebUserArgumentException("startDate should be before endDate ");
        }
    }

    private static void validateDateNotEmpty(LocalDate startDate) {
        if (startDate == null) {
            throw new WrongWebUserArgumentException("date must not be null");
        }
    }

    public static boolean startDateIsInRange(LocalDate startDate, LocalDate endDate, LocalDate dateToCheck) {
        return startDate.isEqual(dateToCheck) || dateIsInRange(startDate, endDate, dateToCheck);
    }
    public static boolean endDateIsInRange(LocalDate startDate, LocalDate endDate, LocalDate dateToCheck) {
        return endDate.isEqual(dateToCheck) || dateIsInRange(startDate, endDate, dateToCheck);
    }

    public static boolean dateIsInRange(LocalDate startDate, LocalDate endDate, LocalDate dateToCheck) {
        return startDate.isBefore(dateToCheck) && endDate.isAfter(dateToCheck);
    }
}
