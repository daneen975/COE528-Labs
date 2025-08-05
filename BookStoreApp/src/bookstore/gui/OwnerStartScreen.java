/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.gui;

import bookstore.BookStoreApp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author dashaf
 */
public class OwnerStartScreen {
    private Scene scene;
    
    public OwnerStartScreen(BookStoreApp app) {
        Text title = new Text("Owner Dashboard");
        title.setFont(Font.font(20));
        
        Button booksButton = new Button("Manage Books");
        booksButton.setOnAction(e -> {
            OwnerBooksScreen booksScreen = new OwnerBooksScreen(app);
            app.getPrimaryStage().setScene(booksScreen.getScene());
        });
        
        Button customersButton = new Button("Manage Customers");
        customersButton.setOnAction(e -> {
            OwnerCustomersScreen customersScreen = new OwnerCustomersScreen(app);
            app.getPrimaryStage().setScene(customersScreen.getScene());
        });
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> app.showLoginScreen());
        
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(
            title, booksButton, customersButton, logoutButton
        );
        
        scene = new Scene(layout, 500, 400);
    }
    
    public Scene getScene() {
        return scene;
    }
}
