/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.gui;

import bookstore.BookStoreApp;
import bookstore.model.Customer;
import bookstore.util.FileHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author dashaf
 */

public class CustomerCostScreen {
    private Scene scene;
    
    public CustomerCostScreen(BookStoreApp app, Customer customer, double totalCost) {
        Text costText = new Text(String.format("Total Cost: $%.2f", totalCost));
        costText.setStyle("-fx-font-size: 16;");
        
        Text pointsText = new Text(String.format(
            "Points: %d, Status: %s", 
            customer.getPoints(), customer.getStatusName()
        ));
        pointsText.setStyle("-fx-font-size: 16;");
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            FileHandler.saveData(FileHandler.loadBooks(), FileHandler.loadCustomers());
            app.showLoginScreen();
        });
        
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(costText, pointsText, logoutButton);
        
        scene = new Scene(layout, 400, 300);
    }
    
    public Scene getScene() {
        return scene;
    }
}

