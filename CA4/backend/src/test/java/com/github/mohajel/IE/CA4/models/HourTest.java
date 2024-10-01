package com.github.mohajel.IE.CA4.models;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.*;

public class HourTest {

    @Test
    public void testToString() {
        Hour hour = new Hour("12:30");
        assertEquals(hour.toString(), "12:30");
    }

    @Test
    public void testIsHourRounded_TrueCondition() {
        Hour hour = new Hour("12:00");
        assertTrue(hour.isHourRounded());
    }

    @Test
    public void testIsHourRounded_FalseCondition() {
        Hour hour = new Hour("12:35");
        assertFalse(hour.isHourRounded());
    }

    @ParameterizedTest
    @CsvSource({
            "12:30, 12:45, 12:31, true",
            "12:30, 12:45, 12:44, true",
            "20:00, 04:00, 02:00, true",
            "14:00, 03:00, 02:00, true",
            "12:30, 00:45, 00:50, false",
            "12:30, 12:45, 12:00, false",
            "12:30, 12:45, 12:59, false",
            "12:30, 12:45, 12:46, false",
            "12:30, 12:45, 12:29, false"
    })
    public void isTimeInRange(String start, String end, String time, boolean expected) {
        Hour startHour = new Hour(start);
        Hour endHour = new Hour(end);
        Hour timeHour = new Hour(time);
        assertEquals(expected, timeHour.isTimeInRange(startHour, endHour));
    }
}
