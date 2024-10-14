package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import com.bnpp.kata.berlinclock.validation.TimeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.bnpp.kata.berlinclock.constants.TestConstants.*;

class BerlinClockServiceTest {

    private BerlinClockService berlinClockService;

    @BeforeEach
    public void setup() {
        TimeValidator timeValidator = new TimeValidator();
        berlinClockService = new BerlinClockService(timeValidator);
    }

    @Test
    @DisplayName("Seconds Lamp : should be ON for even seconds")
    void convertToBerlinTime_passEvenSeconds_secondsLampShouldBeON() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(ZERO).seconds(TWO).build();

        BerlinClockResponse result = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(result.getDetailedBerlinTime().getSecondsLamp()).isEqualTo(YELLOW);
    }

    @Test
    @DisplayName("Seconds Lamp : should be OFF for odd seconds")
    void convertToBerlinTime_passOddSeconds_secondsLampShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(ZERO).seconds(FIVE).build();

        BerlinClockResponse result = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(result.getDetailedBerlinTime().getSecondsLamp()).isEqualTo(OFF);
    }

    @Test
    @DisplayName("Five Hour Row : should be OFF when given hour is less than 5")
    void convertToBerlinTime_passHoursLessThanFive_allFiveHourLampShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWO).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveHourLamps()).isEqualTo(FOUR_LAMPS_OFF);
    }

    @Test
    @DisplayName("Five Hour Row : first lamp should be RED when given hour is between 5 and 9")
    void convertToBerlinTime_passHoursBetweenFiveAndNine_firstLampOfFiveHourRowShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(SIX).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveHourLamps()).isEqualTo(FIRST_LAMP_RED);
    }

    @Test
    @DisplayName("Five Hour Row : first two lamps should be RED when given hour is between 10 and 14")
    void convertToBerlinTime_passHoursBetweenTenAndFourteen_firstTwoLampsOfFiveHourRowShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWELVE).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveHourLamps()).isEqualTo(FIRST_TWO_LAMPS_RED);
    }

    @Test
    @DisplayName("Five Hour Row : first three lamps should be RED when given hour is between 15 and 19")
    void convertToBerlinTime_passHoursBetweenFifteenAndNineteen_firstThreeLampsOfFiveHourRowShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(EIGHTEEN).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveHourLamps()).isEqualTo(FIRST_THREE_LAMPS_RED);
    }

    @Test
    @DisplayName("Five Hour Row : all lamps should be RED when given hour is between 20 and 23")
    void convertToBerlinTime_passHoursBetweenTwentyToTwentyThree_allLampsOfFiveHourRowShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWENTYTHREE).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveHourLamps()).isEqualTo(ALL_FOUR_LAMPS_RED);
    }

    @Test
    @DisplayName("One Hour Row : should be OFF when given hour is divisible by 5")
    void convertToBerlinTime_passHoursDivisibleByFive_allLampOfOneHourRowShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(FIVE).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneHourLamps()).isEqualTo(FOUR_LAMPS_OFF);
    }

    @Test
    @DisplayName("One Hour Row : first lamp should be RED when hour divided by 5 has reminder 1")
    void convertToBerlinTime_whenHourDividedByFiveHasRemainderOne_firstLampShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(SIX).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneHourLamps()).isEqualTo(FIRST_LAMP_RED);
    }

    @Test
    @DisplayName("One Hour Row : first two lamps should be RED when hour divided by 5 has reminder 2")
    void convertToBerlinTime_whenHourDividedByFiveHasRemainderTwo_firstTwoLampsShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWELVE).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneHourLamps()).isEqualTo(FIRST_TWO_LAMPS_RED);
    }

    @Test
    @DisplayName("One Hour Row : first three lamps should be RED when hour divided by 5 has reminder 3")
    void convertToBerlinTime_whenHourDividedByFiveHasRemainderThree_firstThreeLampsShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(EIGHTEEN).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneHourLamps()).isEqualTo(FIRST_THREE_LAMPS_RED);
    }

    @Test
    @DisplayName("One Hour Row : all lamps should be RED when hour divided by 5 has reminder 4")
    void convertToBerlinTime_whenHourDividedByFiveHasRemainderFour_allLampsShouldBeRED() {

        TimeComponent timeComponent = TimeComponent.builder().hours(FOURTEEN).minutes(ZERO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneHourLamps()).isEqualTo(ALL_FOUR_LAMPS_RED);
    }

    @Test
    @DisplayName("Five Minute Row : should be OFF when given minute is less than 5")
    void convertToBerlinTime_passMinuteLessThanFive_allLampsOfFiveMinuteRowShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(TWO).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveMinuteLamps()).isEqualTo(ALL_11_LAMPS_OFF);
    }

    @Test
    @DisplayName("Five Minute Row : first lamp should be YELLOW when given minute is between 5 and 9")
    void convertToBerlinTime_passMinuteBetweenFiveAndNine_firstLampOfFiveMinuteRowShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(SIX).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveMinuteLamps()).isEqualTo(ONE_LAMP_YELLOW_OUT_OF_ELEVEN);
    }

    @Test
    @DisplayName("Five Minute Row : first two lamps should be YELLOW when given minute is between 10 and 14")
    void convertToBerlinTime_passMinutesBetweenTenAndFifteen_firstTwoLampsOfFiveMinuteRowShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(TWELVE).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveMinuteLamps()).isEqualTo(TWO_LAMP_YELLOW_OUT_OF_ELEVEN);
    }

    @Test
    @DisplayName("Five Minute Row : third lamp should be RED when given minute is 15")
    void convertToBerlinTime_passMinuteFifteen_thirdLampOfFiveMinuteRowShouldBeRed() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(FIFTEEN).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveMinuteLamps()).isEqualTo(THIRD_LAMP_RED_OUT_OF_ELEVEN);
    }

    @Test
    @DisplayName("Five Minute Row : lamps should be YELLOW based on minutes divisible by 5; every third lamp should be RED")
    void convertToBerlinTime_whenMinutesDivisibleByFive_lampsShouldBeYellowWithThirdLampRed() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(TWENTY).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getTopFiveMinuteLamps()).isEqualTo(FIVE_MINT_FOURLAMPON);
    }

    @Test
    @DisplayName("One Minute Row : should be OFF when given minute is divisible by 5")
    void convertToBerlinTime_passMinuteDivisibleByFive_allLampsOfOneMinuteRowShouldBeOFF() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(FIVE).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneMinuteLamps()).isEqualTo(FOUR_LAMPS_OFF);
    }

    @Test
    @DisplayName("One Minute Row : first lamp should be YELLOW when minute divided by 5 has reminder 1")
    void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderOne_firstLampShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(SIX).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneMinuteLamps()).isEqualTo(FIRST_LAMP_YELLOW);
    }

    @Test
    @DisplayName("One Minute Row : first two lamps should be YELLOW when minute divided by 5 has reminder 2")
    void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderTwo_firstTwoLampsShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(TWELVE).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneMinuteLamps()).isEqualTo(FIRST_TWO_LAMPS_YELLOW);
    }

    @Test
    @DisplayName("One Minute Row : first three lamps should be YELLOW when minute divided by 5 has reminder 3")
    void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderThree_firstThreeLampShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(EIGHTEEN).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneMinuteLamps()).isEqualTo(FIRST_THREE_LAMPS_YELLOW);
    }

    @Test
    @DisplayName("One Minute Row : all lamps should be YELLOW when minute divided by 5 has reminder 4")
    void convertToBerlinTime_whenMinuteDividedByFiveHasRemainderFour_allLampsShouldBeYellow() {

        TimeComponent timeComponent = TimeComponent.builder().hours(ZERO).minutes(FOURTEEN).seconds(ZERO).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDetailedBerlinTime().getBottomOneMinuteLamps()).isEqualTo(ALL_FOUR_LAMPS_YELLOW);
    }

    @Test
    @DisplayName("Display Digital Time in the response")
    void convertToBerlinTime_passTimeComponents_responseShouldContainDigitalTime() {

        TimeComponent timeComponent = TimeComponent.builder().hours(FOURTEEN).minutes(TWENTYTHREE).seconds(FIVE).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getDigitalTime()).isEqualTo(DIGITAL_TIME);
    }

    @Test
    @DisplayName("Display Berlin Time in the response")
    void convertToBerlinTime_passTimeComponents_responseShouldContainBerlinTime() {

        TimeComponent timeComponent = TimeComponent.builder().hours(TWENTYTHREE).minutes(FIFTYNINE).seconds(FIFTYNINE).build();

        BerlinClockResponse response = berlinClockService.convertToBerlinTime(timeComponent);

        assertThat(response.getBerlinTime()).isEqualTo(BERLIN_TIME);
    }
}
