package com.bnpp.kata.berlinclock.validation;

import com.bnpp.kata.berlinclock.exception.TimeFormatException;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.bnpp.kata.berlinclock.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeValidatorTest {

    private TimeValidator timeValidator;

    @BeforeEach
    public void setup() {
        timeValidator = new TimeValidator();
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input hours are empty")
    void validateTimeValues_checkWhetherTheInputHoursAreNotEmpty_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(EMPTY).minutes(FOURTEEN).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> timeValidator.validateTimeValues(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input minutes are empty")
    void validateTimeValues_checkWhetherTheInputMinutesAreNotEmpty_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(EMPTY).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> timeValidator.validateTimeValues(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input seconds are empty")
    void validateTimeValues_checkWhetherTheInputSecondsAreNotEmpty_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(ZERO).seconds(EMPTY).build();

        assertThrows(TimeFormatException.class, () -> timeValidator.validateTimeValues(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input hour is greater than 23")
    void validateTimeValues_passHourGreaterThan23_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(SEVENTY).minutes(ZERO).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> timeValidator.validateTimeValues(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input hour is less than 0")
    void convertToBerlinTime_passHourLessThanZero_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(MINUS_ONE).minutes(ZERO).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> timeValidator.validateTimeValues(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input minute is greater than 59")
    void convertToBerlinTime_passMinuteGreaterThan59_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(SEVENTY).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> timeValidator.validateTimeValues(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input minute is less than 0")
    void convertToBerlinTime_passMinuteLessThanZero_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(MINUS_ONE).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> timeValidator.validateTimeValues(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input second is greater than 59")
    void convertToBerlinTime_passSecondGreaterThan59_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(ZERO).seconds(SEVENTY).build();

        assertThrows(TimeFormatException.class, () -> timeValidator.validateTimeValues(timeComponent));
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input second is less than 0")
    void convertToBerlinTime_passSecondLessThanZero_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(ZERO).seconds(MINUS_ONE).build();

        assertThrows(TimeFormatException.class, () -> timeValidator.validateTimeValues(timeComponent));
    }
}
