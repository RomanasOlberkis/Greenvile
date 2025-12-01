package com.greenvile.model;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private List<Resident> residents;
    private List<GreenAction> greenActions;
    private List<CommunalGoal> communalGoals;
    private List<Trade> trades;
    private PointSettings pointSettings;
    private int communalPointsPool;

    public DataManager() {
        this.residents = new ArrayList<>();
        this.greenActions = new ArrayList<>();
        this.communalGoals = new ArrayList<>();
        this.trades = new ArrayList<>();
        this.pointSettings = new PointSettings();
        this.communalPointsPool = 0;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public List<GreenAction> getGreenActions() {
        return greenActions;
    }

    public void setGreenActions(List<GreenAction> greenActions) {
        this.greenActions = greenActions;
    }

    public List<CommunalGoal> getCommunalGoals() {
        return communalGoals;
    }

    public void setCommunalGoals(List<CommunalGoal> communalGoals) {
        this.communalGoals = communalGoals;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public PointSettings getPointSettings() {
        return pointSettings;
    }

    public void setPointSettings(PointSettings pointSettings) {
        this.pointSettings = pointSettings;
    }

    public int getCommunalPointsPool() {
        return communalPointsPool;
    }

    public void setCommunalPointsPool(int communalPointsPool) {
        this.communalPointsPool = communalPointsPool;
    }

    public void addToCommunalPool(int points) {
        this.communalPointsPool += points;
    }

    public int generateNewResidentId() {
        int maxId = 0;
        for (Resident r : residents) {
            if (r.getId() > maxId) {
                maxId = r.getId();
            }
        }
        return maxId + 1;
    }

    public int generateNewGreenActionId() {
        int maxId = 0;
        for (GreenAction a : greenActions) {
            if (a.getId() > maxId) {
                maxId = a.getId();
            }
        }
        return maxId + 1;
    }

    public int generateNewCommunalGoalId() {
        int maxId = 0;
        for (CommunalGoal g : communalGoals) {
            if (g.getId() > maxId) {
                maxId = g.getId();
            }
        }
        return maxId + 1;
    }

    public int generateNewTaskId(CommunalGoal goal) {
        int maxId = 0;
        for (CommunalTask t : goal.getTasks()) {
            if (t.getId() > maxId) {
                maxId = t.getId();
            }
        }
        return maxId + 1;
    }

    public int generateNewTradeId() {
        int maxId = 0;
        for (Trade t : trades) {
            if (t.getId() > maxId) {
                maxId = t.getId();
            }
        }
        return maxId + 1;
    }

    public Resident getResidentById(int id) {
        for (Resident r : residents) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
}
