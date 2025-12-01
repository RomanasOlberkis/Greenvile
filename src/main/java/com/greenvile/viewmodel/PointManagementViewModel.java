package com.greenvile.viewmodel;

import com.greenvile.model.*;
import java.time.LocalDate;

public class PointManagementViewModel {
    private DataManager dataManager;

    public PointManagementViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public PointSettings getSettings() {
        return dataManager.getPointSettings();
    }

    public void setResetFrequency(String frequency) {
        dataManager.getPointSettings().setResetFrequency(frequency);
    }

    public void manualReset() {
        int totalPoints = 0;
        for (Resident r : dataManager.getResidents()) {
            totalPoints += r.resetPoints();
        }
        dataManager.addToCommunalPool(totalPoints);
        dataManager.getPointSettings().setLastResetDate(LocalDate.now().toString());
    }

    public int getCommunalPoolTotal() {
        return dataManager.getCommunalPointsPool();
    }

    public String getLastResetDate() {
        return dataManager.getPointSettings().getLastResetDate();
    }
}
