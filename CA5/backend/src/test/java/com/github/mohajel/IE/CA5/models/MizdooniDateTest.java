package com.github.mohajel.IE.CA5.models;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

public class MizdooniDateTest {

    @Test
    public void testMizdooniDate_toString() {
        String expected = "2020-12-31 23:59";
        MizdooniDate mizdooniDate = new MizdooniDate(expected);
        String actual = mizdooniDate.toString();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "2020-12-31 23:59, 2019-12-31 23:59, false",
            "2020-12-31 23:59, 2020-11-31 23:59, false",
            "2020-12-31 23:59, 2020-12-30 23:59, false",
            "2020-12-31 23:59, 2020-12-31 22:58, false",
            "2024-12-31 23:59, 2025-12-31 23:59, true",
            "2024-11-31 23:59, 2024-12-31 23:59, true",
            "2024-12-30 23:59, 2024-12-31 23:58, true",
    })
    public void testMizdooniDate_isBefore(String date1, String date2, boolean expected) {
        MizdooniDate mizdooniDate1 = new MizdooniDate(date1);
        MizdooniDate mizdooniDate2 = new MizdooniDate(date2);
        boolean actual = mizdooniDate1.isBefore(mizdooniDate2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "2020-12-31 23:59, 2019-12-31 23:59, true",
            "2020-12-31 23:59, 2020-11-31 23:59, true",
            "2020-12-31 23:59, 2020-12-30 23:59, true",
            "2020-12-31 23:59, 2020-12-31 22:58, true",
            "2024-12-31 23:59, 2025-12-31 23:59, false",
            "2024-11-31 23:59, 2024-12-31 23:59, false",
            "2024-12-30 23:59, 2024-12-31 23:58, false",
    })
    public void testMizdooniDate_isAfter(String date1, String date2, boolean expected) {
        MizdooniDate mizdooniDate1 = new MizdooniDate(date1);
        MizdooniDate mizdooniDate2 = new MizdooniDate(date2);
        boolean actual = mizdooniDate1.isAfter(mizdooniDate2);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "2020-12-31 23:59, true",
            "2020-12-31 23:00, true",
            "2020-12-31 00:59, true",
            "2020-12-31, false",
            "2020-12-31 23:59:59, false",
            "2020/12/31 23:59, false",
            "23:59, false",
    })
    public void testisDateTimeFormatValid(String date, boolean expected) {
        boolean actual = MizdooniDate.isDateTimeFormatValid(date);
        assertEquals(expected, actual);
    }
}
