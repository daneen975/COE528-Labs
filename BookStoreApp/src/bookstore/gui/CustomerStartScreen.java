/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.gui;

import bookstore.BookStoreApp;
import bookstore.model.Book;
import bookstore.model.Customer;
import bookstore.util.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

/**
 *
 * @author dashaf
 */
public class CustomerStartScreen {
    private Scene scene;
    private Customer customer;
    private ObservableList<Book> booksData;
    
    public CustomerStartScreen(BookStoreApp app, Customer customer) {
        this.customer = customer;
        List<Book> booksList = FileHandler.loadBooks();
        booksData = FXCollections.observableArrayList(booksList);
        
        Text welcomeText = new Text(String.format(
            "Welcome %s. You have %d points. Your status is %s",
            customer.getUsername(), customer.getPoints(), customer.getStatusName()
        ));
        
        TableView<Book> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Book, String> nameColumn = new TableColumn<>("Book Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Book, Double> priceColumn = new TableColumn<>("Book Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn<Book, Boolean> selectColumn = new TableColumn<>("Select");
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        selectColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
        
        table.getColumns().addAll(nameColumn, priceColumn, selectColumn);
        table.setItems(booksData);
        
        Button buyButton = new Button("Buy");
        buyButton.setOnAction(e -> handlePurchase(app, false));
        
        Button redeemButton = new Button("Redeem points and Buy");
        redeemButton.setOnAction(e -> handlePurchase(app, true));
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            FileHandler.saveData(booksData, FileHandler.loadCustomers());
            app.showLoginScreen();
        });
        
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(buyButton, redeemButton, logoutButton);
        buttonBox.setPadding(new Insets(10));
        
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(welcomeText, table, buttonBox);
        
        scene = new Scene(layout, 600, 500);
    }
    
    private void handlePurchase(BookStoreApp app, boolean redeemPoints) {
        double totalCost = 0;
        int pointsToEarn = 0;
        
        for (Book book : booksData) {
            if (book.isSelected()) {
                totalCost += book.getPrice();
                pointsToEarn += (int)(book.getPrice() * 10);
                book.setSelected(false);
            }
        }
        
        if (totalCost > 0) {
            if (redeemPoints) {
                double discount = customer.calculateDiscount(customer.getPoints());
                totalCost = Math.max(0, totalCost - discount);
                customer.redeemPoints();
            }
            
            customer.addPoints(pointsToEarn);
            app.showCustomerCostScreen(customer, totalCost);
        }
    }
    
    public Scene getScene() {
        return scene;
    }
}
