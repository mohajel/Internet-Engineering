package com.github.mohajel.IE.CA1.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MizdooniDate {
    private int year;
    private int month;
    private int day;
    private Hour time;

    public MizdooniDate(String dateTime) {
        String[] parts = dateTime.split(" ");
        String[] dateParts = parts[0].split("-");
        this.year = Integer.parseInt(dateParts[0]);
        this.month = Integer.parseInt(dateParts[1]);
        this.day = Integer.parseInt(dateParts[2]);
        this.time = new Hour(parts[1]);
    }

    public MizdooniDate deepCopy() {
        return new MizdooniDate(this.toString());
    }

    public Hour getTime() {
        return time;
    }

    public String getDateTime() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    public static boolean isDateTimeFormatValid(String dateTime) {
        String format = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        try {
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBefore(MizdooniDate other) {
        if (this.year < other.year) {
            return true;
        } else if (this.year == other.year && this.month < other.month) {
            return true;
        } else if (this.year == other.year && this.month == other.month && this.day < other.day) {
            return true;
        } else if (this.year == other.year && this.month == other.month && this.day == other.day && this.time.isBefore(other.time)) {
            return true;
        }
        return false;
    }

    public boolean isAfter(MizdooniDate other) {
        if (this.year > other.year) {
            return true;
        } else if (this.year == other.year && this.month > other.month) {
            return true;
        } else if (this.year == other.year && this.month == other.month && this.day > other.day) {
            return true;
        } else if (this.year == other.year && this.month == other.month && this.day == other.day && this.time.isAfter(other.time)) {
            return true;
        }
        return false;
    }

    public boolean isHourRounded() {
        return time.isHourRounded();
    }

    public boolean isHourInRange(Hour start, Hour end) {
        return time.isTimeInRange(start, end);
    }

    public boolean isNDaysAfter(MizdooniDate other, int n) {
        return (this.year == other.year) && (this.month == other.month) && (this.day == other.day + n);
    }
    
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d %s", year, month, day, time.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MizdooniDate)) {
            return false;
        }
        MizdooniDate other = (MizdooniDate) obj;
        return (this.year == other.year) && (this.month == other.month) && (this.day == other.day) && (this.time.equals(other.time));
    }
}
