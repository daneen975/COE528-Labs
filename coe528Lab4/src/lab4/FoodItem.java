/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author dashaf
 */
public class FoodItem extends FoodComponent {
    private double price;

    public FoodItem(String name, double price) {
        super(name);
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void print(int level) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < level; i++) {
            tabs.append("\t");
        }
        System.out.println(tabs + "FoodItem: " + name + ", " + price);
    }
}