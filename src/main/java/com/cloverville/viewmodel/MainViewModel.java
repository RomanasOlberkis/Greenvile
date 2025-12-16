/*/ this shit lests classes to talk with one another, or like a middle man. Other view models have to go through here to work properly, otherwise we would not be saving shit in our app */

package com.cloverville.viewmodel;

import com.cloverville.model.*;
import com.cloverville.util.FileHandler;

public class MainViewModel {
    private DataManager dataManager;
    private FileHandler fileHandler;

    public MainViewModel() {
        this.fileHandler = new FileHandler();
        this.dataManager = fileHandler.loadData();
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void saveAllData() {
        fileHandler.saveData(dataManager);
        fileHandler.exportToWebsite(dataManager);
    }
}
