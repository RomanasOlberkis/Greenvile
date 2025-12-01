package com.greenvile.viewmodel;

import com.greenvile.model.*;
import java.util.List;

public class TradeViewModel {
    private DataManager dataManager;

    public TradeViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Trade> getTradeList() {
        return dataManager.getTrades();
    }

    public void addTrade(Trade trade) {
        trade.setId(dataManager.generateNewTradeId());
        dataManager.getTrades().add(trade);
    }

    public void updateTrade(Trade trade) {
        for (int i = 0; i < dataManager.getTrades().size(); i++) {
            if (dataManager.getTrades().get(i).getId() == trade.getId()) {
                dataManager.getTrades().set(i, trade);
                break;
            }
        }
    }

    public void deleteTrade(int id) {
        dataManager.getTrades().removeIf(t -> t.getId() == id);
    }

    public void markCompleted(int id) {
        for (Trade t : dataManager.getTrades()) {
            if (t.getId() == id) {
                t.setCompleted(true);
                break;
            }
        }
    }

    public void toggleWebsiteDisplay(int id) {
        for (Trade t : dataManager.getTrades()) {
            if (t.getId() == id) {
                t.setDisplayOnWebsite(!t.isDisplayOnWebsite());
                break;
            }
        }
    }

    public List<Resident> getAllResidents() {
        return dataManager.getResidents();
    }
}
