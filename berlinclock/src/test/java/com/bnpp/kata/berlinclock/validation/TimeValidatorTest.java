package com.bnpp.kata.berlinclock.validation;

import com.bnpp.kata.berlinclock.exception.TimeFormatException;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.bnpp.kata.berlinclock.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeValidatorTest {

    private TimeValidator timeValidator;

    @BeforeEach
    public void setup() {
        timeValidator = new TimeValidator();
    }

    @Test
    @DisplayName("Throw Time Format Exception : if the input hours are empty")
    public void validateTimeValues_checkWhetherTheInputHoursAreNotEmpty_shouldThrowTimeFormatException() {

        TimeComponent timeComponent = TimeComponent.builder().hours(EMPTY).minutes(FOURTEEN).seconds(ZERO).build();

        assertThrows(TimeFormatException.class, () -> timeValidator.validateTimeValues(timeComponent));
    }
}
