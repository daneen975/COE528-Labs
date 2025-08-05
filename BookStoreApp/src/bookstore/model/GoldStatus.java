/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.model;

/**
 *
 * @author dashaf
 */
public class GoldStatus implements CustomerStatus {
    @Override
    public String getStatusName() {
        return "Gold";
    }
    
    @Override
    public double redeemPoints(int points) {
        return points / 100.0;
    }
}