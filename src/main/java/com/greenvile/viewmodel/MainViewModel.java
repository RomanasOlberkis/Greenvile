package com.greenvile.viewmodel;

import com.greenvile.model.*;
import com.greenvile.util.FileHandler;
import java.time.LocalDate;

public class MainViewModel {
    private DataManager dataManager;
    private FileHandler fileHandler;

    public MainViewModel() {
        this.fileHandler = new FileHandler();
        this.dataManager = fileHandler.loadData();
        checkPointReset();
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void saveAllData() {
        fileHandler.saveData(dataManager);
        fileHandler.exportToWebsite(dataManager);
    }

    public void checkPointReset() {
        if (dataManager.getPointSettings().isResetDue()) {
            performPointReset();
        }
    }

    public void performPointReset() {
        int totalPoints = 0;
        for (Resident r : dataManager.getResidents()) {
            totalPoints += r.resetPoints();
        }
        dataManager.addToCommunalPool(totalPoints);
        dataManager.getPointSettings().setLastResetDate(LocalDate.now().toString());
        saveAllData();
    }
}
