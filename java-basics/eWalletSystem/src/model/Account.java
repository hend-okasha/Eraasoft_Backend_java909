package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String userName;
    private String password;
    private double balance;
    private String phoneNumber;
    private String address;
    private float age;
    private List<History> history = new ArrayList<>();
    private boolean isAdmin;
    private boolean isActive;

    public Account() {
        this.isAdmin = false;
        this.isActive = true;
    }

    public Account(String userName) {
        this.userName = userName;
        this.history = new ArrayList<>();
        this.isAdmin = false;
        this.isActive = true;
    }

    public Account(String userName , String password) {
        this.password = password;
        this.userName = userName;
        this.history = new ArrayList<>();
        this.isAdmin = false;
        this.isActive = true;
    }

    public Account(String userName, String password, String phoneNumber, String address, float age) {
        this.userName = userName;
        this.password = password;
        this.balance = 0;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
        this.history = new ArrayList<>();
        this.isAdmin = false;
        this.isActive = true;
    }

    public Account(String userName, String password, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.balance = 0;
        this.history = new ArrayList<>();
        this.isAdmin = isAdmin;
        this.isActive = true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "\n========== Account Details ==========\n" +
                "Username      : " + userName + "\n" +
                "Balance       : " + balance + " EGP\n" +
                "Phone Number  : " + (phoneNumber != null ? phoneNumber : "N/A") + "\n" +
                "Address       : " + (address != null ? address : "N/A") + "\n" +
                "Age           : " + age + "\n" +
                "Status        : " + (isActive ? "Active" : "Inactive") + "\n" +
                "Role          : " + (isAdmin ? "Admin" : "User") + "\n" +
                "====================================";
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }
}
