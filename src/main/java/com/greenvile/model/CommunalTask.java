package com.greenvile.model;

public class CommunalTask {
    private int id;
    private String title;
    private String description;
    private int pointsAwarded;
    private boolean completed;
    private boolean displayOnWebsite;

    public CommunalTask() {
        this.id = 0;
        this.title = "";
        this.description = "";
        this.pointsAwarded = 0;
        this.completed = false;
        this.displayOnWebsite = false;
    }

    public CommunalTask(int id, String title, String description, int pointsAwarded) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pointsAwarded = pointsAwarded;
        this.completed = false;
        this.displayOnWebsite = false;
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

    public int getPointsAwarded() {
        return pointsAwarded;
    }

    public void setPointsAwarded(int pointsAwarded) {
        this.pointsAwarded = pointsAwarded;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isDisplayOnWebsite() {
        return displayOnWebsite;
    }

    public void setDisplayOnWebsite(boolean displayOnWebsite) {
        this.displayOnWebsite = displayOnWebsite;
    }

    public String toString() {
        return title;
    }
}
