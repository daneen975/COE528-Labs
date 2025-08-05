/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3;

/**
 *
 * @author dashaf
 */

import java.util.ArrayList;

public class QueueOfDistinctStrings {

    // Overview: QueueOfDistinctStrings are mutable, bounded
    // collection of distinct strings that operate in
    // FIFO (First-In-First-Out) order.
    //
    // The abstraction function is:
    // AF(c) = { c.items.get(0), c.items.get(1), ..., c.items.get(n-1) }
    // where n = c.items.size(), and c.items.get(0) is the front of the queue,
    // and c.items.get(n-1) is the end of the queue.
    //
    // The rep invariant is:
    // RI(c) = true if all elements in c.items are distinct (no duplicates),
    // and c.items != null.
    //
    // The rep
    private ArrayList<String> items;

    // constructor
    public QueueOfDistinctStrings() {
        // EFFECTS: Creates a new QueueOfDistinctStrings object
        items = new ArrayList<String>();
    }

    // MODIFIES: this
    // EFFECTS: Appends the element at the end of the queue
    // if the element is not in the queue, otherwise
    // does nothing.
    public void enqueue(String element) throws Exception {
        if (element == null) throw new Exception("Element cannot be null");
        if (!items.contains(element))
            items.add(element);
    }

    // MODIFIES: this
    // EFFECTS: Removes an element from the front of the queue
    public String dequeue() throws Exception {
        if (items.size() == 0) throw new Exception("Queue is empty");
        return items.remove(0);
    }

    // EFFECTS: Returns true if the rep invariant holds for this
    // object; otherwise returns false
    public boolean repOK() {
        if (items == null) return false;
        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).equals(items.get(j))) {
                    return false; // Duplicate found
                }
            }
        }
        return true; // No duplicates and items is not null
    }

    // EFFECTS: Returns a string that contains the strings in the
    // queue, the front element and the end element.
    // Implements the abstraction function.
    public String toString() {
        if (items.size() == 0) return "Queue is empty";
        String result = "Queue: [";
        for (int i = 0; i < items.size(); i++) {
            result += items.get(i);
            if (i < items.size() - 1) result += ", ";
        }
        result += "], Front: " + items.get(0) + ", End: " + items.get(items.size() - 1);
        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        try {
            QueueOfDistinctStrings queue = new QueueOfDistinctStrings();

            // Test enqueue
            queue.enqueue("ab");
            queue.enqueue("cd");
            queue.enqueue("ae");
            queue.enqueue("bd");
            System.out.println("After enqueue: " + queue.toString());

            // Test enqueue with duplicate (should not add)
            queue.enqueue("ab");
            System.out.println("After enqueue (duplicate): " + queue.toString());

            // Test dequeue
            System.out.println("Dequeued: " + queue.dequeue());
            System.out.println("After dequeue: " + queue.toString());

            // Test repOK
            System.out.println("Rep invariant holds: " + queue.repOK());

            // Test dequeue until empty
            queue.dequeue();
            queue.dequeue();
            queue.dequeue();
            System.out.println("After dequeue all: " + queue.toString());

            // Test dequeue on empty queue (should throw exception)
            queue.dequeue();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}