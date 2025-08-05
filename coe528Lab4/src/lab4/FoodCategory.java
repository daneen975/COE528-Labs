/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author dashaf
 */
import java.util.ArrayList;
import java.util.List;

public class FoodCategory extends FoodComponent {
    private List<FoodComponent> components = new ArrayList<>();

    public FoodCategory(String name) {
        super(name);
    }

    public void add(FoodComponent component) {
        components.add(component);
    }

    public void remove(FoodComponent component) {
        components.remove(component);
    }

    @Override
    public double getPrice() {
        double totalPrice = 0.0;
        for (FoodComponent component : components) {
            totalPrice += component.getPrice();
        }
        return totalPrice;
    }

    @Override
    public void print(int level) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < level; i++) {
            tabs.append("\t");
        }
        System.out.println(tabs + "FoodCategory (" + name + ", " + getPrice() + ") contains:");
        for (FoodComponent component : components) {
            component.print(level + 1);
        }
    }
}
