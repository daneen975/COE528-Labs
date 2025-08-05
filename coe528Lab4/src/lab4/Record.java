/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author dashaf
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Record {
    // Name of the associated file
    private String filename;
    private static Record instance = null;

    // Private constructor to enforce singleton pattern
    private Record(String n) {
        filename = n;
    }

    // Singleton instance getter
    public static Record getInstance(String filename) {
        if (instance == null) {
            instance = new Record(filename);
        }
        return instance;
    }

    // Effects: Reads and prints the contents of the associated file to the standard output.
    public void read() {
        try (Scanner scanner = new Scanner(new FileReader(filename))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    // Effects: Appends the specified message, msg, to the associated file.
    public void write(String msg) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(msg);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Obtain the sole instance of the Record class
        Record r = Record.getInstance("record.txt");

        // Do not modify the code below
        r.write("Hello-1\n");
        r.write("Hello-2\n");
        System.out.println("Currently the file record.txt contains the following lines:");
        r.read();
    }
}
