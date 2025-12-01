package com.greenvile.model;

public class Person {
    private int id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private String picturePath;

    public Person() {
        this.id = 0;
        this.fullName = "";
        this.phoneNumber = "";
        this.email = "";
        this.address = "";
        this.picturePath = "";
    }

    public Person(int id, String fullName, String phoneNumber, String email, String address, String picturePath) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String toString() {
        return fullName;
    }
}
