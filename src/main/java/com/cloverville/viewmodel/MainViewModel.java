/*/ this shit lests classes to talk with one another, or like a middle man. Other view models have to go through here to work properly, otherwise we would not be saving shit in our app */

package com.cloverville.viewmodel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.cloverville.model.*;
import com.cloverville.util.FileHandler;

public class MainViewModel {
    private DataManager dataManager;
    private FileHandler fileHandler;

    public MainViewModel() {
        this.fileHandler = new FileHandler();
        this.dataManager = fileHandler.loadData();
        checkMonthlyReset();
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void saveAllData() {
        fileHandler.saveData(dataManager);
        fileHandler.exportToWebsite(dataManager);
    }
     private void checkMonthlyReset() {
        String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        String lastReset = dataManager.getLastResetMonth();
        if (lastReset == null || lastReset.isEmpty() || !lastReset.equals(currentMonth)) {
            performMonthlyReset();
            dataManager.setLastResetMonth(currentMonth);
            saveAllData();
            
            System.out.println("Monthly reset performed for: " + currentMonth);
        }
    }
        private void performMonthlyReset() {
        int totalPoints = 0;
        for (Resident r : dataManager.getResidents()) {
            totalPoints += r.getPersonalPoints();
        }
        for (Resident r : dataManager.getResidents()) {
            r.setPersonalPoints(0);
        }
        for (CommunalGoal goal : dataManager.getCommunalGoals()) {
            if (goal.isActive() && !goal.isCompleted()) {
                goal.addPoints(totalPoints);
            }
        }
        
        System.out.println("Reset " + totalPoints + " points from residents to active goals");
    }
}
