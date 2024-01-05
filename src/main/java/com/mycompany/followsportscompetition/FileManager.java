/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.followsportscompetition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class FileManager {

 
    public static void writeToFile(String filePath, List<String> data) {
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (String line : data) {
                writer.write(line);
                writer.newLine(); // Add a new line for the next entry
            }
            writer.close();
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static List<String> readFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            System.out.println("Data has been read from the file.");
            return lines;
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }
        return null;
    }

}