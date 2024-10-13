package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import com.bnpp.kata.berlinclock.validation.TimeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static com.bnpp.kata.berlinclock.constants.TestConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParameterizedServiceTest {

    private BerlinClockService berlinClockService;

    @BeforeEach
    public void setup() {
        TimeValidator timeValidator = new TimeValidator();
        berlinClockService = new BerlinClockService(timeValidator);
    }

    @ParameterizedTest
    @DisplayName("Berlin Time should be displayed in the response for various inputs")
    @CsvSource({
            "23, 59, 59, " + BERLIN_TIME ,
            "00, 00, 0, " + ZERO_BERLIN_TIME,
            "14, 24, 5, " + FORTEEN_BERLIN_TIME
    })
    public void convertToBerlinTime_passHoursMinutesSeconds_responseShouldContainBerlinTime(String hour, String minute, String second, String expectedBerlinTime) {

        TimeComponent timeComponent = TimeComponent.builder().hours(hour).minutes(minute).seconds(second).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getBerlinTime()).isEqualTo(expectedBerlinTime);
    }
}
