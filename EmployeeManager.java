// File Name: EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {

    // ===============================
    //  FILE READ / WRITE UTIL METHODS
    // ===============================

    // Read employees.txt and return content as a single string
    private static String readEmployees() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("employees.txt")));

            return reader.readLine();
        } catch (Exception ex) {
            System.out.println("Error reading file.");
            return "";
        }
    }

    // Write data to employees.txt (append = true/false)
    private static void writeEmployees(String data, boolean append) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("employees.txt", append));

            writer.write(data);
            writer.close();
        } catch (Exception ex) {
            System.out.println("Error writing file.");
        }
    }

    // ===============================
    //  MAIN APPLICATION LOGIC
    // ===============================

    public static void main(String[] args) {

        // Argument count check (Task #2)
        if (args.length != 1) {
            System.out.println("Error: Invalid number of arguments.");
            System.out.println("Usage: java EmployeeManager <option>");
            return;
        }

        String option = args[0];

        // LIST ALL EMPLOYEES
        if (option.equals("l")) {
            System.out.println("Loading data ...");

            String data = readEmployees();
            String employees[] = data.split(",");

            for (String emp : employees) {
                System.out.println(emp);
            }

            System.out.println("Data Loaded.");

        // SHOW RANDOM EMPLOYEE
        } else if (option.equals("s")) {
            System.out.println("Loading data ...");

            String data = readEmployees();
            String employees[] = data.split(",");

            Random random = new Random();
            int index = random.nextInt(employees.length);

            System.out.println(employees[index]);
            System.out.println("Data Loaded.");

        // ADD NEW EMPLOYEE
        } else if (option.contains("+")) {
            System.out.println("Loading data ...");

            String newEmployee = option.substring(1);
            writeEmployees(", " + newEmployee, true);

            System.out.println("Data Loaded.");

        // SEARCH EMPLOYEE
        } else if (option.contains("?")) {
            System.out.println("Loading data ...");

            String data = readEmployees();
            String employees[] = data.split(",");

            String searchName = option.substring(1);
            boolean found = false;

            for (String emp : employees) {
                if (emp.equals(searchName)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Employee found!");
            } else {
                System.out.println("Employee not found.");
            }

            System.out.println("Data Loaded.");

        // COUNT WORDS
        } else if (option.equals("c")) {
            System.out.println("Loading data ...");

            String data = readEmployees();
            char[] chars = data.toCharArray();

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

        // UPDATE EMPLOYEE
        } else if (option.contains("u")) {
            System.out.println("Loading data ...");

            String data = readEmployees();
            String employees[] = data.split(",");

            String employeeToUpdate = option.substring(1);

            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(employeeToUpdate)) {
                    employees[i] = "Updated";
                }
            }

            writeEmployees(String.join(",", employees), false);

            System.out.println("Data Updated.");

        // DELETE EMPLOYEE
        } else if (option.contains("d")) {
            System.out.println("Loading data ...");

            String data = readEmployees();
            String employees[] = data.split(",");

            String employeeToDelete = option.substring(1);

            List<String> list = new ArrayList<>(Arrays.asList(employees));
            list.remove(employeeToDelete);

            writeEmployees(String.join(",", list), false);

            System.out.println("Data Deleted.");
        }
    }
}
