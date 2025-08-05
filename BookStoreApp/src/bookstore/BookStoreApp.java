/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;

import bookstore.gui.LoginScreen;
import bookstore.gui.OwnerStartScreen;
import bookstore.gui.CustomerStartScreen;
import bookstore.gui.CustomerCostScreen;
import bookstore.model.Customer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author dashaf
 */
public class BookStoreApp extends Application {
    
    static {
    System.setProperty("javafx.verbose", "true"); // Debug if needed
}
    
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("BookStore App");
        
        showLoginScreen();
        primaryStage.show();
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public void showLoginScreen() {
        LoginScreen loginScreen = new LoginScreen(this);
        primaryStage.setScene(loginScreen.getScene());
    }
    
    public void showOwnerStartScreen() {
        OwnerStartScreen ownerStartScreen = new OwnerStartScreen(this);
        primaryStage.setScene(ownerStartScreen.getScene());
    }
    
    public void showCustomerStartScreen(Customer customer) {
        CustomerStartScreen customerStartScreen = new CustomerStartScreen(this, customer);
        primaryStage.setScene(customerStartScreen.getScene());
    }
    
    public void showCustomerCostScreen(Customer customer, double totalCost) {
        CustomerCostScreen customerCostScreen = new CustomerCostScreen(this, customer, totalCost);
        primaryStage.setScene(customerCostScreen.getScene());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}