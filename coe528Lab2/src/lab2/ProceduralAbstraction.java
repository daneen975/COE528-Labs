/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

/**
 *
 * @author dashaf
 */
public class ProceduralAbstraction {

    // Ex1: Implementation of reverseFactorial procedure
    // Requires: None
    // Modifies: None
    // Effects: Returns the smallest positive integer n for which n! (i.e. 1*2*3*...*n) is greater than or equal to x, for positive integer x. Otherwise returns 1.
    public static int reverseFactorial(int x) {
        if (x <= 0) {
            return 1; // Return 1 for non-positive inputs as per the specification
        }

        int n = 1;
        int factorial = 1;

        while (factorial < x) {
            n++;
            factorial *= n;
        }

        return n;
    }

    // Ex2: Implementation of isMatrixNice procedure
    // Requires: None
    // Modifies: None
    // Effects: If the matrix arr satisfies Nice property, prints the sum and returns true. Otherwise returns false.
    public static boolean isMatrixNice(int[][] arr) {
        if (arr == null || arr.length == 0 || arr.length != arr[0].length) {
            return false; // Not a square matrix
        }

        int n = arr.length;
        int sum = 0;

        // Calculate the sum of the first row (reference sum)
        for (int j = 0; j < n; j++) {
            sum += arr[0][j];
        }

        // Check row sums
        for (int i = 1; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += arr[i][j];
            }
            if (rowSum != sum) {
                return false;
            }
        }

        // Check column sums
        for (int j = 0; j < n; j++) {
            int colSum = 0;
            for (int i = 0; i < n; i++) {
                colSum += arr[i][j];
            }
            if (colSum != sum) {
                return false;
            }
        }

        // Check main diagonal sum
        int diagSum1 = 0;
        for (int i = 0; i < n; i++) {
            diagSum1 += arr[i][i];
        }
        if (diagSum1 != sum) {
            return false;
        }

        // Check secondary diagonal sum
        int diagSum2 = 0;
        for (int i = 0; i < n; i++) {
            diagSum2 += arr[i][n - 1 - i];
        }
        if (diagSum2 != sum) {
            return false;
        }

        // If all checks pass, print the sum and return true
        System.out.println("Sum: " + sum);
        return true;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test reverseFactorial
        System.out.println("reverseFactorial(24): " + reverseFactorial(24)); // Expected: 4
        System.out.println("reverseFactorial(119): " + reverseFactorial(119)); // Expected: 5
        System.out.println("reverseFactorial(1): " + reverseFactorial(1)); // Expected: 1
        System.out.println("reverseFactorial(0): " + reverseFactorial(0)); // Expected: 1

        // Test isMatrixNice
        int[][] niceMatrix = {
            {2, 7, 6},
            {9, 5, 1},
            {4, 3, 8}
        };
        System.out.println("isMatrixNice(niceMatrix): " + isMatrixNice(niceMatrix)); // Expected: true

        int[][] notNiceMatrix = {
            {-3, 1, 0},
            {4, -3, 4},
            {7, -9, 0}
        };
        System.out.println("isMatrixNice(notNiceMatrix): " + isMatrixNice(notNiceMatrix)); // Expected: false
    }
}
