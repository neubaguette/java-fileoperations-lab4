package lab4_fileoperations;

import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class Main {

    public static ArrayList<String> generateData() {

        ArrayList<String> data = new ArrayList<>();
        Random random = new Random();

        String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < 10; i++) {

            StringBuilder element = new StringBuilder();

            for (int j = 0; j < 6; j++) {
                int index = random.nextInt(symbols.length());
                element.append(symbols.charAt(index));
            }

            data.add(element.toString());
        }

        return data;
    }

    public static void writeToFile(ArrayList<String> data) {

        File file = new File("1.txt");

        try (FileWriter writer = new FileWriter(file)) {

            for (String element : data) {
                writer.write(element + System.lineSeparator());
            }

            System.out.println("\nData successfully written to 1.txt");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<String> readFromFile() {

        ArrayList<String> fileData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("1.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                fileData.add(line);
            }

            System.out.println("\nFile 1.txt successfully read.");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return fileData;
    }

    public static String[] getUserElements() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter elements separated by commas:");
        String input = scanner.nextLine();

        String[] elements = input.split(",");

        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].trim();
        }

        return elements;
    }

    public static ArrayList<Integer> searchElements(
            ArrayList<String> fileData, String[] userElements) {

        ArrayList<Integer> foundLines = new ArrayList<>();

        for (String userElement : userElements) {

            boolean found = false;

            for (int i = 0; i < fileData.size(); i++) {

                if (fileData.get(i).equals(userElement)) {

                    foundLines.add(i + 1);

                    System.out.println("Match found: "
                            + userElement + " - line " + (i + 1));

                    found = true;
                }
            }

            if (!found) {
                System.out.println("Element not found: " + userElement);
            }
        }

        return foundLines;
    }

    public static void saveResults(ArrayList<Integer> foundLines) {

        File file = new File("2.txt");

        if (foundLines.isEmpty()) {

            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("\nOld 2.txt file deleted.");
                } else {
                    System.out.println("\nUnable to delete old 2.txt file.");
                }
            }

            System.out.println("No matches found. File 2.txt was not created.");
            return;
        }

        try (FileWriter writer = new FileWriter(file)) {

            for (Integer lineNumber : foundLines) {
                writer.write(lineNumber + System.lineSeparator());
            }

            System.out.println("\nSearch results successfully saved to 2.txt");

        } catch (IOException e) {
            System.out.println("Error writing results: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        System.out.println("Laboratory work #4");
        System.out.println("Working with files in Java");

        ArrayList<String> data = generateData();

        System.out.println("\nGenerated elements:");

        for (String element : data) {
            System.out.println(element);
        }

        writeToFile(data);

        ArrayList<String> fileData = readFromFile();

        System.out.println("\nFile contents:");

        for (String element : fileData) {
            System.out.println(element);
        }

        System.out.println("\nNumber of lines in file: " + fileData.size());

        String[] userElements = getUserElements();

        System.out.println("\nEntered elements:");

        for (String element : userElements) {
            System.out.println(element);
        }

        System.out.println("\nSearch results:");

        ArrayList<Integer> foundLines = searchElements(fileData, userElements);

        System.out.println("\nTotal matches found: " + foundLines.size());

        saveResults(foundLines);
    }

}