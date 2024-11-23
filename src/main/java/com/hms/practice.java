package com.hms;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class practice {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Input array
        System.out.println("Enter the size of the array:");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        // Input target number
        System.out.println("Enter the target number:");
        int target = scanner.nextInt();

        // Find closest number
        int closest = findClosest(array, target);
        System.out.println("The closest number to " + target + " is " + closest);

        scanner.close();
    }

    // Method to find closest number
    public static int findClosest(int[] array, int target) {
        int closest = array[0];
        int minDifference = Math.abs(target - closest);

        for (int i = 1; i < array.length; i++) {
            int difference = Math.abs(target - array[i]);
            if (difference < minDifference) {
                minDifference = difference;
                closest = array[i];
            }
        }

        return closest;

    }
}



