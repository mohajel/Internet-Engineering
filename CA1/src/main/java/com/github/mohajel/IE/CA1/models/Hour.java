package com.github.mohajel.IE.CA1.models;

public class Hour {
    private int hours;
    private int minutes;

    public Hour(String time) {
        String[] parts = time.split(":");
        this.hours = Integer.parseInt(parts[0]);
        this.minutes = Integer.parseInt(parts[1]);
    }

    public Hour deepCopy() {
        return new Hour(this.toString());
    }

    public int getJustHours() {
        if (minutes > 0)
            return hours + 1;
        return hours;
    }

    public boolean isBefore(Hour other) {
        if (this.hours < other.hours) {
            return true;
        } else if (this.hours == other.hours && this.minutes < other.minutes) {
            return true;
        }
        return false;
    }

    public boolean isAfter(Hour other) {
        if (this.hours > other.hours) {
            return true;
        } else if (this.hours == other.hours && this.minutes > other.minutes) {
            return true;
        }
        return false;
    }

    public boolean isTimeInRange(Hour start, Hour end) {
        // if start & end are not in the same day
        if (end.isBefore(start)) {
            return (this.isAfter(start) || this.equals(start)) || (this.isBefore(end) || this.equals(end));
        } else {
            return this.isAfter(start) && this.isBefore(end);
        }
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Hour)) {
            return false;
        }
        Hour other = (Hour) obj;
        return (this.hours == other.hours) && (this.minutes == other.minutes);
    }

    public boolean isHourRounded() {
        return minutes == 0;
    }
}
