package com.seol.booksrv.utils;

import com.seol.booksrv.exception.WrongWebUserArgumentException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateRangeUtilsTest {

    @Test
    void validateDateRangeBasic_shouldPass_whenStartDateIsBeforeEndDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);

        assertDoesNotThrow(() -> DateRangeUtils.validateDateRangeBasic(startDate, endDate));
    }

    @Test
    void validateDateRangeBasic_shouldThrowException_whenStartDateIsNull() {
        LocalDate endDate = LocalDate.of(2025, 12, 10);

        WrongWebUserArgumentException exception = assertThrows(
                WrongWebUserArgumentException.class,
                () -> DateRangeUtils.validateDateRangeBasic(null, endDate)
        );
        assertEquals("date must not be null", exception.getMessage());
    }

    @Test
    void validateDateRangeBasic_shouldThrowException_whenEndDateIsNull() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);

        WrongWebUserArgumentException exception = assertThrows(
                WrongWebUserArgumentException.class,
                () -> DateRangeUtils.validateDateRangeBasic(startDate, null)
        );
        assertEquals("date must not be null", exception.getMessage());
    }

    @Test
    void validateDateRangeBasic_shouldThrowException_whenStartDateIsAfterEndDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 10);
        LocalDate endDate = LocalDate.of(2025, 12, 1);

        WrongWebUserArgumentException exception = assertThrows(
                WrongWebUserArgumentException.class,
                () -> DateRangeUtils.validateDateRangeBasic(startDate, endDate)
        );
        assertEquals("startDate should be before endDate ", exception.getMessage());
    }

    @Test
    void validateDateRangeBasic_shouldThrowException_whenStartDateEqualsEndDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 1);

        WrongWebUserArgumentException exception = assertThrows(
                WrongWebUserArgumentException.class,
                () -> DateRangeUtils.validateDateRangeBasic(startDate, endDate)
        );
        assertEquals("startDate should be before endDate ", exception.getMessage());
    }

    // startDateIsInRange tests

    @Test
    void startDateIsInRange_shouldReturnTrue_whenDateToCheckEqualsStartDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 1);

        assertTrue(DateRangeUtils.startDateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void startDateIsInRange_shouldReturnTrue_whenDateToCheckIsInRange() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 5);

        assertTrue(DateRangeUtils.startDateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void startDateIsInRange_shouldReturnFalse_whenDateToCheckIsBeforeStartDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 11, 30);

        assertFalse(DateRangeUtils.startDateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void startDateIsInRange_shouldReturnFalse_whenDateToCheckIsAfterEndDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 11);

        assertFalse(DateRangeUtils.startDateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void startDateIsInRange_shouldReturnFalse_whenDateToCheckEqualsEndDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 10);

        assertFalse(DateRangeUtils.startDateIsInRange(startDate, endDate, dateToCheck));
    }

    // endDateIsInRange tests

    @Test
    void endDateIsInRange_shouldReturnTrue_whenDateToCheckEqualsEndDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 10);

        assertTrue(DateRangeUtils.endDateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void endDateIsInRange_shouldReturnTrue_whenDateToCheckIsInRange() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 5);

        assertTrue(DateRangeUtils.endDateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void endDateIsInRange_shouldReturnFalse_whenDateToCheckIsBeforeStartDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 11, 30);

        assertFalse(DateRangeUtils.endDateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void endDateIsInRange_shouldReturnFalse_whenDateToCheckIsAfterEndDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 11);

        assertFalse(DateRangeUtils.endDateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void endDateIsInRange_shouldReturnFalse_whenDateToCheckEqualsStartDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 1);

        assertFalse(DateRangeUtils.endDateIsInRange(startDate, endDate, dateToCheck));
    }

    // dateIsInRange tests

    @Test
    void dateIsInRange_shouldReturnTrue_whenDateToCheckIsStrictlyBetweenDates() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 5);

        assertTrue(DateRangeUtils.dateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void dateIsInRange_shouldReturnFalse_whenDateToCheckEqualsStartDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 1);

        assertFalse(DateRangeUtils.dateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void dateIsInRange_shouldReturnFalse_whenDateToCheckEqualsEndDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 10);

        assertFalse(DateRangeUtils.dateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void dateIsInRange_shouldReturnFalse_whenDateToCheckIsBeforeStartDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 11, 30);

        assertFalse(DateRangeUtils.dateIsInRange(startDate, endDate, dateToCheck));
    }

    @Test
    void dateIsInRange_shouldReturnFalse_whenDateToCheckIsAfterEndDate() {
        LocalDate startDate = LocalDate.of(2025, 12, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 10);
        LocalDate dateToCheck = LocalDate.of(2025, 12, 11);

        assertFalse(DateRangeUtils.dateIsInRange(startDate, endDate, dateToCheck));
    }
}
