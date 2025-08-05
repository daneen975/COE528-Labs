/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.gui;

import bookstore.BookStoreApp;
import bookstore.model.Book;
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
public class OwnerBooksScreen {
    private Scene scene;
    private ObservableList<Book> booksData;
    
    public OwnerBooksScreen(BookStoreApp app) {
        List<Book> booksList = FileHandler.loadBooks();
        booksData = FXCollections.observableArrayList(booksList);
        
        // Create table
        TableView<Book> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Book, String> nameColumn = new TableColumn<>("Book Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Book, Double> priceColumn = new TableColumn<>("Book Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        table.getColumns().addAll(nameColumn, priceColumn);
        table.setItems(booksData);
        
        // Add book controls
        TextField nameField = new TextField();
        nameField.setPromptText("Book Name");
        nameField.setPrefWidth(150);
        
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        priceField.setPrefWidth(100);
        
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                
                if (!name.isEmpty() && price >= 0) {
                    booksData.add(new Book(name, price));
                    nameField.clear();
                    priceField.clear();
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price format");
            }
        });
        
        HBox addBox = new HBox(10);
        addBox.getChildren().addAll(
            new Label("Name:"), nameField, 
            new Label("Price:"), priceField, addButton
        );
        addBox.setPadding(new Insets(10));
        
        // Bottom controls
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Book selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                booksData.remove(selected);
            }
        });
        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> app.showOwnerStartScreen());
        
        Button saveButton = new Button("Save Changes");
        saveButton.setOnAction(e -> FileHandler.saveBooks(booksData));
        
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
