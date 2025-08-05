/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.gui;

import bookstore.BookStoreApp;
import bookstore.model.Customer;
import bookstore.util.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 *
 * @author dashaf
 */
public class OwnerCustomersScreen {
    private Scene scene;
    private ObservableList<Customer> customersData;
    
    public OwnerCustomersScreen(BookStoreApp app) {
        List<Customer> customersList = FileHandler.loadCustomers();
        customersData = FXCollections.observableArrayList(customersList);
        
        // Create table
        TableView<Customer> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Customer, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        
        TableColumn<Customer, String> passwordCol = new TableColumn<>("Password");
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        TableColumn<Customer, Integer> pointsCol = new TableColumn<>("Points");
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        table.getColumns().addAll(usernameCol, passwordCol, pointsCol);
        table.setItems(customersData);
        
        // Add customer controls
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        
        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");
        
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            
            if (!username.isEmpty() && !password.isEmpty()) {
                customersData.add(new Customer(username, password));
                usernameField.clear();
                passwordField.clear();
            }
        });
        
        HBox addBox = new HBox(10);
        addBox.getChildren().addAll(
            new Label("Username:"), usernameField, 
            new Label("Password:"), passwordField, addButton
        );
        addBox.setPadding(new Insets(10));
        
        // Bottom controls
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Customer selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                customersData.remove(selected);
            }
        });
        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> app.showOwnerStartScreen());
        
        Button saveButton = new Button("Save Changes");
        saveButton.setOnAction(e -> FileHandler.saveCustomers(customersData));
        
        HBox bottomBox = new HBox(10);
        bottomBox.getChildren().addAll(deleteButton, saveButton, backButton);
        bottomBox.setPadding(new Insets(10));
        
        // Layout
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(table);
        borderPane.setTop(addBox);
        borderPane.setBottom(bottomBox);
        
        scene = new Scene(borderPane, 600, 400);
    }
    
    public Scene getScene() {
        return scene;
    }
}
