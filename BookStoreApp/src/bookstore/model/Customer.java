/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.model;

/**
 *
 * @author dashaf
 */
public class Customer {
    private String username;
    private String password;
    private int points;
    private CustomerStatus status;
    
    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
        this.status = new SilverStatus();
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getPoints() { return points; }
    public String getStatusName() { return status.getStatusName(); }
    
    public void setPoints(int points) {
        this.points = points;
        updateStatus();
    }
    
    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
        updateStatus();
    }
    
    private void updateStatus() {
        if (points >= 1000) {
            status = new GoldStatus();
        } else {
            status = new SilverStatus();
        }
    }
    
    public double redeemPoints() {
        int pointsToRedeem = points;
        double discount = status.redeemPoints(pointsToRedeem);
        points -= pointsToRedeem;
        updateStatus();
        return discount;
    }
    
    public double calculateDiscount(int pointsToRedeem) {
        return status.redeemPoints(pointsToRedeem);
    }
    
    @Override
    public String toString() {
        return username + "," + password + "," + points;
    }
}
