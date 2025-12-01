package com.greenvile.model;

import java.util.ArrayList;
import java.util.List;

public class GreenAction {
    private int id;
    private String title;
    private String description;
    private String picturePath;
    private int pointsAwarded;
    private boolean displayOnWebsite;
    private List<Integer> participantIds;

    public GreenAction() {
        this.id = 0;
        this.title = "";
        this.description = "";
        this.picturePath = "";
        this.pointsAwarded = 0;
        this.displayOnWebsite = false;
        this.participantIds = new ArrayList<>();
    }

    public GreenAction(int id, String title, String description, String picturePath, int pointsAwarded) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.picturePath = picturePath;
        this.pointsAwarded = pointsAwarded;
        this.displayOnWebsite = false;
        this.participantIds = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getPointsAwarded() {
        return pointsAwarded;
    }

    public void setPointsAwarded(int pointsAwarded) {
        this.pointsAwarded = pointsAwarded;
    }

    public boolean isDisplayOnWebsite() {
        return displayOnWebsite;
    }

    public void setDisplayOnWebsite(boolean displayOnWebsite) {
        this.displayOnWebsite = displayOnWebsite;
    }

    public List<Integer> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(List<Integer> participantIds) {
        this.participantIds = participantIds;
    }

    public void addParticipant(int residentId) {
        if (!participantIds.contains(residentId)) {
            participantIds.add(residentId);
        }
    }

    public void removeParticipant(int residentId) {
        participantIds.remove(Integer.valueOf(residentId));
    }

    public String toString() {
        return title;
    }
}
