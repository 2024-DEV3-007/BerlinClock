package com.bnpp.kata.berlinclock.constants;

public class Constants {

	private Constants() {
	}

	public static final int ZERO = 0;
	public static final int HOUR_DIVIDER = 5;
	public static final int MINUTES_DIVIDER = 5;
	public static final int SECONDS_DIVIDER = 2;
	public static final String REPLACE_YYY = "YYY";
	public static final String REPLACE_TO_YYR = "YYR";
	public static final String TIME_IS_EMPTY_ERROR = "Invalid Time. Time cannot be null or empty.";
	public static final String INVALID_HOUR_ERROR = "Hours must be between 0 and 23.";
	public static final String INVALID_MINUTE_ERROR = "Minutes must be between 0 and 59.";
	public static final String INVALID_SECOND_ERROR = "Seconds must be between 0 and 59.";
	public static final int MAX_HOURS = 23;
	public static final int MAX_MINUTES = 59;
	public static final int MAX_SECONDS = 59;
	public static final String TIME_SEPARATOR = ":";
	public static final String TIME_FORMAT = "%02d";
	public static final String DELIMITER = " ";
	public static final String INVALID_REQUEST = "Invalid Request";
}
