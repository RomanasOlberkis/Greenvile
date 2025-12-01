package com.greenvile.viewmodel;

import com.greenvile.model.*;
import java.util.List;

public class GreenActionViewModel {
    private DataManager dataManager;

    public GreenActionViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<GreenAction> getActionList() {
        return dataManager.getGreenActions();
    }

    public void addAction(GreenAction action) {
        action.setId(dataManager.generateNewGreenActionId());
        dataManager.getGreenActions().add(action);
        awardPointsToParticipants(action);
    }

    public void updateAction(GreenAction action) {
        for (int i = 0; i < dataManager.getGreenActions().size(); i++) {
            if (dataManager.getGreenActions().get(i).getId() == action.getId()) {
                dataManager.getGreenActions().set(i, action);
                break;
            }
        }
    }

    public void deleteAction(int id) {
        dataManager.getGreenActions().removeIf(a -> a.getId() == id);
    }

    public void toggleWebsiteDisplay(int id) {
        for (GreenAction a : dataManager.getGreenActions()) {
            if (a.getId() == id) {
                a.setDisplayOnWebsite(!a.isDisplayOnWebsite());
                break;
            }
        }
    }

    public void awardPointsToParticipants(GreenAction action) {
        for (int residentId : action.getParticipantIds()) {
            Resident r = dataManager.getResidentById(residentId);
            if (r != null) {
                r.addPoints(action.getPointsAwarded());
            }
        }
    }

    public List<Resident> getAllResidents() {
        return dataManager.getResidents();
    }
}
