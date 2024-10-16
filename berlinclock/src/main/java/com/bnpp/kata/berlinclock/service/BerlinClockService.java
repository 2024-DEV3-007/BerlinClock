package com.bnpp.kata.berlinclock.service;

import com.bnpp.kata.berlinclock.model.BerlinClockResponse;
import com.bnpp.kata.berlinclock.model.DetailedBerlinTime;
import com.bnpp.kata.berlinclock.model.TimeComponent;
import com.bnpp.kata.berlinclock.store.Lamp;
import com.bnpp.kata.berlinclock.store.LampRow;
import com.bnpp.kata.berlinclock.validation.TimeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.bnpp.kata.berlinclock.constants.Constants.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BerlinClockService {

    private final TimeValidator timeValidator;

    public BerlinClockResponse convertToBerlinTime(TimeComponent time) {

        timeValidator.validateTimeValues(time);
        Map<String, String> lamps = calculateLamps(time);

        return BerlinClockResponse.builder()
                .digitalTime(convertToDigitalTime(time))
                .detailedBerlinTime(createDetailedBerlinTime(lamps))
                .berlinTime(calculateBerlinTime(lamps))
                .build();
    }

    private String calculateBerlinTime(Map<String, String> lamps) {

        return String.join(DELIMITER,
                lamps.get(LampRow.SECONDS_LAMP.getName()),
                lamps.get(LampRow.TOP_HOUR_LAMP.getName()),
                lamps.get(LampRow.BOTTOM_HOUR_LAMP.getName()),
                lamps.get(LampRow.TOP_MINUTE_LAMP.getName()),
                lamps.get(LampRow.BOTTOM_MINUTE_LAMP.getName())
        );
    }

    private Map<String, String> calculateLamps(TimeComponent time) {

        Map<String, String> lamps = new HashMap<>();
        int hours = Integer.parseInt(time.getHours());
        int minutes = Integer.parseInt(time.getMinutes());
        int seconds = Integer.parseInt(time.getSeconds());

        lamps.put(LampRow.SECONDS_LAMP.getName(), getSecondsLamp(seconds));
        lamps.put(LampRow.TOP_HOUR_LAMP.getName(), getHoursLamp(LampRow.TOP_HOUR_LAMP.getLength(), hours / HOUR_DIVIDER));
        lamps.put(LampRow.BOTTOM_HOUR_LAMP.getName(), getHoursLamp(LampRow.BOTTOM_HOUR_LAMP.getLength(), hours % HOUR_DIVIDER));
        lamps.put(LampRow.TOP_MINUTE_LAMP.getName(), getMinuteLamp(LampRow.TOP_MINUTE_LAMP.getLength(), minutes / MINUTES_DIVIDER, true) );
        lamps.put(LampRow.BOTTOM_MINUTE_LAMP.getName(), getMinuteLamp(LampRow.BOTTOM_MINUTE_LAMP.getLength(), minutes % MINUTES_DIVIDER, false));

        return lamps;
    }

    private String getMinuteLamp(int rowLength, int minuteValue, boolean isTopRow) {

        String mintLamps = IntStream.range(ZERO, rowLength)
                .mapToObj(lampIndex -> (lampIndex < minuteValue) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue())
                .collect(Collectors.joining());

        return isTopRow ? mintLamps.replace(REPLACE_YYY, REPLACE_TO_YYR) : mintLamps;
    }

    private DetailedBerlinTime createDetailedBerlinTime(Map<String, String> lamps) {

        return DetailedBerlinTime.builder()
                .secondsLamp(lamps.get(LampRow.SECONDS_LAMP.getName()))
                .topFiveHourLamps(lamps.get(LampRow.TOP_HOUR_LAMP.getName()))
                .bottomOneHourLamps(lamps.get(LampRow.BOTTOM_HOUR_LAMP.getName()))
                .topFiveMinuteLamps(lamps.get(LampRow.TOP_MINUTE_LAMP.getName()))
                .bottomOneMinuteLamps(lamps.get(LampRow.BOTTOM_MINUTE_LAMP.getName()))
                .build();
    }

    private String getHoursLamp(int rowLength, int hourValue) {

        return IntStream.range(ZERO, rowLength)
                .mapToObj(lampIndex -> (lampIndex < hourValue) ? Lamp.RED.getValue() : Lamp.OFF.getValue())
                .collect(Collectors.joining());
    }

    private String getSecondsLamp(int seconds) {
        return (seconds % SECONDS_DIVIDER == ZERO) ? Lamp.YELLOW.getValue() : Lamp.OFF.getValue();
    }

    private String convertToDigitalTime(TimeComponent time) {
        return Arrays.stream(new int[] { Integer.parseInt(time.getHours()), Integer.parseInt(time.getMinutes()),Integer.parseInt(time.getSeconds()) })
                .mapToObj(timeValue -> String.format(TIME_FORMAT, timeValue))
                .collect(Collectors.joining(TIME_SEPARATOR));
    }
}
