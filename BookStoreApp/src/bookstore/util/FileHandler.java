/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.util;

import bookstore.model.Book;
import bookstore.model.Customer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dashaf
 */
public class FileHandler {
    private static final String BOOKS_FILE = "books.txt";
    private static final String CUSTOMERS_FILE = "customers.txt";
    
    public static void saveData(List<Book> books, List<Customer> customers) {
        saveBooks(books);
        saveCustomers(customers);
    }
    
    public static void saveBooks(List<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                writer.println(book.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveCustomers(List<Customer> customers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer customer : customers) {
                writer.println(customer.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    books.add(new Book(parts[0], Double.parseDouble(parts[1])));
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, return empty list
        }
        return books;
    }
    
    public static List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Customer customer = new Customer(parts[0], parts[1]);
                    customer.setPoints(Integer.parseInt(parts[2]));
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, return empty list
        }
        return customers;
    }
}