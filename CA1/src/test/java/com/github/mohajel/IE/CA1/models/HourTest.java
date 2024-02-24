package com.github.mohajel.IE.CA1.models;

import org.junit.Test;

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
}
