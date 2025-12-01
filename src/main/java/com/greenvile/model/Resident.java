package com.greenvile.model;

public class Resident extends Person {
    private int personalPoints;

    public Resident() {
        super();
        this.personalPoints = 0;
    }

    public Resident(int id, String fullName, String phoneNumber, String email, String address, String picturePath, int personalPoints) {
        super(id, fullName, phoneNumber, email, address, picturePath);
        this.personalPoints = personalPoints;
    }

    public int getPersonalPoints() {
        return personalPoints;
    }

    public void setPersonalPoints(int personalPoints) {
        this.personalPoints = personalPoints;
    }

    public void addPoints(int points) {
        this.personalPoints += points;
    }

    public int resetPoints() {
        int points = this.personalPoints;
        this.personalPoints = 0;
        return points;
    }
}
