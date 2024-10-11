package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BerlinClockServiceTest {

    private BerlinClockService berlinClockService;
    private static final String ZERO = "00";
    private static final String TWO = "02";
    private static final String FIVE = "05";
    private static final String YELLOW = "Y";
    private static final String OFF = "O";

    @BeforeEach
    public void setup() {
        berlinClockService = new BerlinClockService();
    }

    @Test
    @DisplayName("Seconds Lamp : should be ON for even seconds")
    public void convertToBerlinTime_passEvenSeconds_secondsLampShouldBeON() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(ZERO).seconds(TWO).build();

        BerlinClockResponse result = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(result.getDetailedBerlinTime().getSecondsLamp()).isEqualTo(YELLOW);
    }

    @Test
    @DisplayName("Seconds Lamp : should be OFF for odd seconds")
    public void convertToBerlinTime_passOddSeconds_secondsLampShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(ZERO).seconds(FIVE).build();

        BerlinClockResponse result = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(result.getDetailedBerlinTime().getSecondsLamp()).isEqualTo(OFF);
    }
}
