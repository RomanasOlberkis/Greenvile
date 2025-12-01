package com.greenvile.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PointSettings {
    private String resetFrequency;
    private String lastResetDate;

    public PointSettings() {
        this.resetFrequency = "Monthly";
        this.lastResetDate = LocalDate.now().toString();
    }

    public PointSettings(String resetFrequency, String lastResetDate) {
        this.resetFrequency = resetFrequency;
        this.lastResetDate = lastResetDate;
    }

    public String getResetFrequency() {
        return resetFrequency;
    }

    public void setResetFrequency(String resetFrequency) {
        this.resetFrequency = resetFrequency;
    }

    public String getLastResetDate() {
        return lastResetDate;
    }

    public void setLastResetDate(String lastResetDate) {
        this.lastResetDate = lastResetDate;
    }

    public boolean isResetDue() {
        LocalDate lastReset = LocalDate.parse(lastResetDate);
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(lastReset, today);

        if (resetFrequency.equals("Weekly")) {
            return daysBetween >= 7;
        } else if (resetFrequency.equals("Monthly")) {
            return daysBetween >= 30;
        } else if (resetFrequency.equals("Yearly")) {
            return daysBetween >= 365;
        }
        return false;
    }
}
