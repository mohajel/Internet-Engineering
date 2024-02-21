package com.github.mohajel.IE.CA1.models;

public class Hour {
    private int hours;
    private int minutes;

    public Hour(String time) {
        String[] parts = time.split(":");
        this.hours = Integer.parseInt(parts[0]);
        this.minutes = Integer.parseInt(parts[1]);
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

    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }
}
