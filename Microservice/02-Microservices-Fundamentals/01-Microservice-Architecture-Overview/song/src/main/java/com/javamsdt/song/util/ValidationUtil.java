package com.javamsdt.song.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ValidationUtil {

    public static Integer getIntegerFromString(String stringNumber) {
        if (stringNumber == null) {
            return 0;
        }

        try {
            return Integer.parseInt(stringNumber);
        } catch (NumberFormatException numberFormatException) {
            log.error(String.format("Error while converting String %s as an integer number, ", stringNumber) + numberFormatException.getMessage(), numberFormatException);
            return 0;
        }
    }

    public static Double getDoubleFromString(String stringNumber) {
        if (stringNumber == null) {
            return 0D;
        }

        try {
            return Double.parseDouble(stringNumber);
        } catch (NumberFormatException numberFormatException) {
            log.error(String.format("Error while converting String %s as a double number ", stringNumber), numberFormatException);
            return 0D;
        }
    }
}
