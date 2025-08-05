/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
/**
 *
 * @author dashaf
 */
public class Book {
    private String name;
    private double price;
    private BooleanProperty selected = new SimpleBooleanProperty(false);
    
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isSelected() { return selected.get(); }
    public BooleanProperty selectedProperty() { return selected; }
    
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setSelected(boolean selected) { this.selected.set(selected); }
    
    @Override
    public String toString() {
        return name + "," + price;
    }
}
