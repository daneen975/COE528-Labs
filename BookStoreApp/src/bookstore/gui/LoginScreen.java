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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

/**
 *
 * @author dashaf
 */
public class LoginScreen {
    private Scene scene;
    
    public LoginScreen(BookStoreApp app) {
        Text title = new Text("Welcome to the BookStore App");
        title.setFont(Font.font(20));
        
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            if (username.equals("admin") && password.equals("admin")) {
                app.showOwnerStartScreen();
            } else {
                List<Customer> customers = FileHandler.loadCustomers();
                for (Customer customer : customers) {
                    if (customer.getUsername().equals(username) && 
                        customer.getPassword().equals(password)) {
                        app.showCustomerStartScreen(customer);
                        return;
                    }
                }
                // Show error for invalid login
                System.out.println("Invalid username or password");
            }
        });
        
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
            title, usernameLabel, usernameField, 
            passwordLabel, passwordField, loginButton
        );
        
        scene = new Scene(layout, 400, 400);
    }
    
    public Scene getScene() {
        return scene;
    }
}
