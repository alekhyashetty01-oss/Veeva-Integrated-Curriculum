package com.studentapp;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "student.txt";
    private static final StudentService service = new StudentService(FILE_NAME);
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("----- STUDENT RECORD MANAGEMENT SYSTEM -----------");

        while (running) {
            displayMenu();
            int choice = readIntInput("Enter your choice (1-8): ");

            switch (choice) {
                case 1:
                    handleAddRecord();
                    break;
                case 2:
                    handleReadAllRecords();
                    break;
                case 3:
                    handleReadByRollNo();
                    break;
                case 4:
                    handleReadByName();
                    break;
                case 5:
                    handleDeleteByRollNo();
                    break;
                case 6:
                    handleUpdateRecord();
                    break;
                case 7:
                    handleGradeReport();
                    break;
                case 8:
                    System.out.println("\nThank you for using Student Management System. Exiting!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 8.");
            }
            if (running) {
                System.out.println("\nPress Enter to return to the menu...");
                scanner.nextLine();
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n------------------- MAIN MENU -------------------");
        System.out.println("1. Add Record");
        System.out.println("2. Read All Records");
        System.out.println("3. Read Record on Roll No");
        System.out.println("4. Read Record on Name");
        System.out.println("5. Delete Record on Roll No");
        System.out.println("6. Update Record on Roll No, Age, Name");
        System.out.println("7. Grade (Report / Individual Lookup)");
        System.out.println("8. Exit");
        System.out.println("-------------------------------------------------");
    }

    // 1. Add Record
    private static void handleAddRecord() {
        System.out.println("\n--- [1] Add New Student Record ---");
        int rollNo = readIntInput("Enter Roll Number: ");
        if (service.findStudentByRollNo(rollNo) != null) {
            System.out.println("Error: Student with Roll No " + rollNo + " already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();

        int age = readIntInput("Enter Age: ");
        double m1 = readDoubleInput("Enter Marks 1: ");
        double m2 = readDoubleInput("Enter Marks 2: ");
        double m3 = readDoubleInput("Enter Marks 3: ");
        double m4 = readDoubleInput("Enter Marks 4: ");

        Student student = new Student(rollNo, name, age, m1, m2, m3, m4);
        if (service.addStudent(student)) {
            System.out.println("Student record added successfully to " + FILE_NAME + "!");
        } else {
            System.out.println("Failed to add record.");
        }
    }

    // 2. Read All Records
    private static void handleReadAllRecords() {
        System.out.println("\n--- [2] All Student Records ---");
        List<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No records found in " + FILE_NAME + ".");
            return;
        }

        printHeader();
        for (Student s : students) {
            printStudentRow(s);
        }
        System.out.println("Total Records: " + students.size());
    }

    // 3. Read Record on Roll No
    private static void handleReadByRollNo() {
        System.out.println("\n--- [3] Search Student by Roll No ---");
        int rollNo = readIntInput("Enter Roll Number to search: ");
        Student s = service.findStudentByRollNo(rollNo);
        if (s != null) {
            System.out.println("Record Found:");
            printHeader();
            printStudentRow(s);
        } else {
            System.out.println("No student found with Roll No: " + rollNo);
        }
    }

    // 4. Read Record on Name
    private static void handleReadByName() {
        System.out.println("\n--- [4] Search Student by Name ---");
        System.out.print("Enter Name (or part of name): ");
        String name = scanner.nextLine().trim();
        List<Student> results = service.findStudentsByName(name);
        if (results.isEmpty()) {
            System.out.println("No students found matching: \"" + name + "\"");
        } else {
            System.out.println("Records Found (" + results.size() + "):");
            printHeader();
            for (Student s : results) {
                printStudentRow(s);
            }
        }
    }

    // 5. Delete Record on Roll No
    private static void handleDeleteByRollNo() {
        System.out.println("\n--- [5] Delete Student Record ---");
        int rollNo = readIntInput("Enter Roll Number to delete: ");
        Student existing = service.findStudentByRollNo(rollNo);
        if (existing == null) {
            System.out.println("Cannot delete: Student with Roll No " + rollNo + " does not exist.");
            return;
        }

        System.out.print("Are you sure you want to delete " + existing.getName() + " (Roll No: " + rollNo + ")? (y/n): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            if (service.deleteStudentByRollNo(rollNo)) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Failed to delete record.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    // 6. Update Record on Roll No, Age, Name
    private static void handleUpdateRecord() {
        System.out.println("\n--- [6] Update Student Record ---");
        int rollNo = readIntInput("Enter Roll Number to update: ");
        Student existing = service.findStudentByRollNo(rollNo);
        if (existing == null) {
            System.out.println("Student with Roll No " + rollNo + " not found.");
            return;
        }

        System.out.println("Current Details: " + existing.getName() + " | Age: " + existing.getAge());
        System.out.print("Enter New Name (leave blank to keep '" + existing.getName() + "'): ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) {
            newName = existing.getName();
        }

        System.out.print("Enter New Age (or press Enter to keep " + existing.getAge() + "): ");
        String ageInput = scanner.nextLine().trim();
        int newAge = ageInput.isEmpty() ? existing.getAge() : Integer.parseInt(ageInput);

        if (service.updateStudent(rollNo, newName, newAge)) {
            System.out.println("Student record updated successfully!");
        } else {
            System.out.println("Failed to update record.");
        }
    }

    // 7. Grade Calculation / Display
    private static void handleGradeReport() {
        System.out.println("\n--- [7] Student Grades & Evaluation ---");
        System.out.println("1. View Grades for All Students");
        System.out.println("2. View Grade for a Specific Roll Number");
        int subChoice = readIntInput("Select an option (1-2): ");

        if (subChoice == 1) {
            List<Student> students = service.getAllStudents();
            if (students.isEmpty()) {
                System.out.println("No records found.");
                return;
            }
            System.out.println(String.format("%-10s | %-18s | %-8s | %-8s | %s", "Roll No", "Name", "Total", "Average", "Grade"));
            System.out.println("----------------------------------------------------------------");
            for (Student s : students) {
                System.out.println(String.format("%-10d | %-18s | %-8.1f | %-8.1f | %s",
                        s.getRollNo(), s.getName(), s.getTotalMarks(), s.getAverageMarks(), s.calculateGrade()));
            }
        } else if (subChoice == 2) {
            int rollNo = readIntInput("Enter Roll Number: ");
            Student s = service.findStudentByRollNo(rollNo);
            if (s != null) {
                System.out.println("\n--- Grade Card ---");
                System.out.println("Roll Number : " + s.getRollNo());
                System.out.println("Name        : " + s.getName());
                System.out.println("Age         : " + s.getAge());
                System.out.println("Marks       : [" + s.getMarks1() + ", " + s.getMarks2() + ", " + s.getMarks3() + ", " + s.getMarks4() + "]");
                System.out.println("Total Marks : " + s.getTotalMarks() + " / 400");
                System.out.printf("Average     : %.2f%%%n", s.getAverageMarks());
                System.out.println("Final Grade : " + s.calculateGrade());
            } else {
                System.out.println("Student with Roll No " + rollNo + " not found.");
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // Helper methods for clean console IO
    private static void printHeader() {
        System.out.println(String.format("%-8s | %-16s | %-5s | %-6s | %-6s | %-6s | %-6s | %-7s | %s",
                "RollNo", "Name", "Age", "M1", "M2", "M3", "M4", "Avg", "Grade"));
        System.out.println("---------------------------------------------------------------------------------");
    }

    private static void printStudentRow(Student s) {
        System.out.println(String.format("%-8d | %-16s | %-5d | %-6.1f | %-6.1f | %-6.1f | %-6.1f | %-7.1f | %s",
                s.getRollNo(), s.getName(), s.getAge(),
                s.getMarks1(), s.getMarks2(), s.getMarks3(), s.getMarks4(),
                s.getAverageMarks(), s.calculateGrade()));
    }

    private static int readIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter an integer.");
            }
        }
    }

    private static double readDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                double val = Double.parseDouble(line);
                if (val < 0 || val > 100) {
                    System.out.println("Please enter marks between 0 and 100.");
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid decimal number.");
            }
        }
    }
}
