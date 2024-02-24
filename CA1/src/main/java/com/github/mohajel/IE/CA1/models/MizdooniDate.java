package com.github.mohajel.IE.CA1.models;

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
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d %s", year, month, day, time.toString());
    }
}
