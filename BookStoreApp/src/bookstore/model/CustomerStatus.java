/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.model;

/**
 *
 * @author dashaf
 */
public interface CustomerStatus {
    String getStatusName();
    double redeemPoints(int points);
}