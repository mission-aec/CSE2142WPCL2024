// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // ===============================
    //  FILE HANDLING METHODS (Task #4)
    // ===============================

    // Reads the employees.txt file and returns its content as a single string
    private static String readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("employees.txt")));

            return reader.readLine();
        } catch (Exception e) {
            System.out.println("Error reading file.");
            return "";
        }
    }

    // Writes data to employees.txt
    private static void writeToFile(String data, boolean append) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt", append));
            writer.write(data);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error writing to file.");
        }
    }

    // ===============================
    //  MAIN LOGIC
    // ===============================

    public static void main(String[] args) {

        // Argument count check (Task #2)
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments.");
            System.out.println("Usage: java EmployeeManager <option>");
            return;
        }

        String option = args[0];

        // LIST employees
        if (option.equals("l")) {
            System.out.println("Loading data ...");

            String line = readFromFile();
            String employees[] = line.split(",");

            for (String emp : employees) {
                System.out.println(emp);
            }

            System.out.println("Data Loaded.");

        // SHOW random employee
        } else if (option.equals("s")) {
            System.out.println("Loading data ...");

            String line = readFromFile();
            String employees[] = line.split(",");

            Random rand = new Random();
            int index = rand.nextInt(employees.length);

            System.out.println(employees[index]);
            System.out.println("Data Loaded.");

        // ADD employee
        } else if (option.contains("+")) {
            System.out.println("Loading data ...");

            String name = option.substring(1);
            writeToFile(", " + name, true);

            System.out.println("Data Loaded.");

        // SEARCH employee
        } else if (option.contains("?")) {
            System.out.println("Loading data ...");

            String line = readFromFile();
            String employees[] = line.split(",");

            String target = option.substring(1);
            boolean found = false;

            for (String emp : employees) {
                if (emp.equals(target)) {
                    System.out.println("Employee found!");
                    found = true;
                    break;
                }
            }

            if (!found) System.out.println("Employee not found.");

            System.out.println("Data Loaded.");

        // COUNT words
        } else if (option.equals("c")) {
            System.out.println("Loading data ...");

            String line = readFromFile();
            char[] chars = line.toCharArray();

            int wordCount = 0;
            boolean insideWord = false;

            for (char c : chars) {
                if (c == ' ') {
                    if (!insideWord) {
                        wordCount++;
                        insideWord = true;
                    }
                } else {
                    insideWord = false;
                }
            }

            System.out.println(wordCount + " word(s) found, " + chars.length + " characters.");
            System.out.println("Data Loaded.");

        // UPDATE employee
        } else if (option.contains("u")) {
            System.out.println("Loading data ...");

            String line = readFromFile();
            String employees[] = line.split(",");

            String nameToUpdate = option.substring(1);

            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(nameToUpdate)) {
                    employees[i] = "Updated";
                }
            }

            writeToFile(String.join(",", employees), false);

            System.out.println("Data Updated.");

        // DELETE employee
        } else if (option.contains("d")) {
            System.out.println("Loading data ...");

            String line = readFromFile();
            String employees[] = line.split(",");

            String nameToDelete = option.substring(1);

            List<String> list = new ArrayList<>(Arrays.asList(employees));
            list.remove(nameToDelete);

            writeToFile(String.join(",", list), false);

            System.out.println("Data Deleted.");
        }
    }
}
