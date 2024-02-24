package com.github.mohajel.IE.CA1.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HourTest {

    @Test
    public void testToString() {
        Hour hour = new Hour("12:30");
        assertEquals(hour.toString(), "12:30");
    }
}
