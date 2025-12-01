package com.greenvile.model;

import java.util.ArrayList;
import java.util.List;

public class CommunalGoal {
    private int id;
    private String title;
    private String description;
    private int pointsNeeded;
    private int currentPoints;
    private boolean completed;
    private List<CommunalTask> tasks;

    public CommunalGoal() {
        this.id = 0;
        this.title = "";
        this.description = "";
        this.pointsNeeded = 0;
        this.currentPoints = 0;
        this.completed = false;
        this.tasks = new ArrayList<>();
    }

    public CommunalGoal(int id, String title, String description, int pointsNeeded) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pointsNeeded = pointsNeeded;
        this.currentPoints = 0;
        this.completed = false;
        this.tasks = new ArrayList<>();
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

    public int getPointsNeeded() {
        return pointsNeeded;
    }

    public void setPointsNeeded(int pointsNeeded) {
        this.pointsNeeded = pointsNeeded;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void addPoints(int points) {
        this.currentPoints += points;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<CommunalTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<CommunalTask> tasks) {
        this.tasks = tasks;
    }

    public void addTask(CommunalTask task) {
        tasks.add(task);
    }

    public void removeTask(CommunalTask task) {
        tasks.remove(task);
    }

    public double getProgressPercentage() {
        if (pointsNeeded == 0) {
            return 0;
        }
        return (double) currentPoints / pointsNeeded * 100;
    }

    public String toString() {
        return title;
    }
}
