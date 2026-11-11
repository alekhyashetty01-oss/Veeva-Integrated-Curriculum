package com.studentapp;

import com.studentapp.model.Student;
import com.studentapp.repository.FileStudentRepository;
import com.studentapp.repository.StudentRepository;
import com.studentapp.service.StudentService;
import com.studentapp.service.StudentServiceImpl;
import com.studentapp.util.ConsoleReader;

import java.util.List;

public class 1Main {
    private static final String DATA_FILE = "student.txt";

    public static void main(String[] args) {
        // Applying Dependency Injection (Inversion of Control)
        StudentRepository repository = new FileStudentRepository(DATA_FILE);
        StudentService service = new StudentServiceImpl(repository);

        boolean running = true;
        System.out.println("===============================================================");
        System.out.println("   OOP-BASED STUDENT RECORD MANAGEMENT SYSTEM (Java I/O)      ");
        System.out.println("===============================================================");

        while (running) {
            displayMenu();
            int choice = ConsoleReader.readInt("Enter choice (1-8): ");

            switch (choice) {
                case 1:
                    handleAddRecord(service);
                    break;
                case 2:
                    handleReadAllRecords(service);
                    break;
                case 3:
                    handleReadByRollNo(service);
                    break;
                case 4:
                    handleReadByName(service);
                    break;
                case 5:
                    handleDeleteByRollNo(service);
                    break;
                case 6:
                    handleUpdateRecord(service);
                    break;
                case 7:
                    handleGradeReport(service);
                    break;
                case 8:
                    System.out.println("\nThank you for using the system. Terminating cleanly!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid menu choice. Select between 1 and 8.");
            }

            if (running) {
                ConsoleReader.pause();
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n---------------------- MAIN MENU ----------------------");
        System.out.println("1. Add Record");
        System.out.println("2. Read All Records");
        System.out.println("3. Read Record on Roll No");
        System.out.println("4. Read Record on Name");
        System.out.println("5. Delete Record on Roll No");
        System.out.println("6. Update Record on Roll No, Age, Name");
        System.out.println("7. Grade (Report / Individual Evaluation)");
        System.out.println("8. Exit");
        System.out.println("-------------------------------------------------------");
    }

    private static void handleAddRecord(StudentService service) {
        System.out.println("\n--- [1] Add Student Record ---");
        int rollNo = ConsoleReader.readInt("Enter Roll Number: ");
        if (service.getRecordByRollNo(rollNo) != null) {
            System.out.println("Error: Student with Roll No " + rollNo + " already exists!");
            return;
        }

        String name = ConsoleReader.readString("Enter Student Name: ");
        int age = ConsoleReader.readInt("Enter Age: ");
        double m1 = ConsoleReader.readDouble("Enter Marks 1 (0-100): ", 0, 100);
        double m2 = ConsoleReader.readDouble("Enter Marks 2 (0-100): ", 0, 100);
        double m3 = ConsoleReader.readDouble("Enter Marks 3 (0-100): ", 0, 100);
        double m4 = ConsoleReader.readDouble("Enter Marks 4 (0-100): ", 0, 100);

        Student student = new Student(rollNo, name, age, m1, m2, m3, m4);
        if (service.addStudentRecord(student)) {
            System.out.println("Record successfully saved to " + DATA_FILE + "!");
        } else {
            System.out.println("Failed to save record.");
        }
    }

    private static void handleReadAllRecords(StudentService service) {
        System.out.println("\n--- [2] All Records ---");
        List<Student> list = service.getAllRecords();
        if (list.isEmpty()) {
            System.out.println("No records found in " + DATA_FILE);
            return;
        }
        printHeader();
        list.forEach(Main::printRow);
        System.out.println("Total Count: " + list.size());
    }

    private static void handleReadByRollNo(StudentService service) {
        System.out.println("\n--- [3] Search by Roll No ---");
        int rollNo = ConsoleReader.readInt("Enter Roll Number: ");
        Student s = service.getRecordByRollNo(rollNo);
        if (s != null) {
            printHeader();
            printRow(s);
        } else {
            System.out.println("No student found with Roll No: " + rollNo);
        }
    }

    private static void handleReadByName(StudentService service) {
        System.out.println("\n--- [4] Search by Name ---");
        String name = ConsoleReader.readString("Enter Student Name (or substring): ");
        List<Student> results = service.getRecordsByName(name);
        if (results.isEmpty()) {
            System.out.println("No records found for: \"" + name + "\"");
        } else {
            printHeader();
            results.forEach(Main::printRow);
        }
    }

    private static void handleDeleteByRollNo(StudentService service) {
        System.out.println("\n--- [5] Delete Record ---");
        int rollNo = ConsoleReader.readInt("Enter Roll Number: ");
        Student s = service.getRecordByRollNo(rollNo);
        if (s == null) {
            System.out.println("Student with Roll No " + rollNo + " does not exist.");
            return;
        }

        String confirm = ConsoleReader.readString("Are you sure you want to delete " + s.getName() + " (Roll No " + rollNo + ")? (y/n): ");
        if (confirm.equalsIgnoreCase("y")) {
            if (service.deleteRecordByRollNo(rollNo)) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Failed to delete record.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void handleUpdateRecord(StudentService service) {
        System.out.println("\n--- [6] Update Record ---");
        int rollNo = ConsoleReader.readInt("Enter Roll Number: ");
        Student s = service.getRecordByRollNo(rollNo);
        if (s == null) {
            System.out.println("Student with Roll No " + rollNo + " does not exist.");
            return;
        }

        System.out.println("Current -> Name: " + s.getName() + ", Age: " + s.getAge());
        String newName = ConsoleReader.readString("Enter New Name (leave blank to keep '" + s.getName() + "'): ");
        if (newName.isEmpty()) newName = s.getName();

        String ageStr = ConsoleReader.readString("Enter New Age (leave blank to keep " + s.getAge() + "): ");
        int newAge = ageStr.isEmpty() ? s.getAge() : Integer.parseInt(ageStr);

        if (service.updateStudentRecord(rollNo, newName, newAge)) {
            System.out.println("Record updated successfully!");
        } else {
            System.out.println("Failed to update record.");
        }
    }

    private static void handleGradeReport(StudentService service) {
        System.out.println("\n--- [7] Grade Evaluation ---");
        System.out.println("1. Grade Report for All Students");
        System.out.println("2. Grade Card by Roll No");
        int choice = ConsoleReader.readInt("Enter choice (1-2): ");

        if (choice == 1) {
            List<Student> list = service.getAllRecords();
            if (list.isEmpty()) {
                System.out.println("No records found.");
                return;
            }
            System.out.println(String.format("%-10s | %-18s | %-8s | %-8s | %s", "Roll No", "Name", "Total", "Average", "Grade"));
            System.out.println("----------------------------------------------------------------");
            for (Student s : list) {
                System.out.println(String.format("%-10d | %-18s | %-8.1f | %-8.1f | %s",
                        s.getRollNo(), s.getName(), s.calculateTotal(), s.calculateAverage(), s.calculateGrade()));
            }
        } else if (choice == 2) {
            int rollNo = ConsoleReader.readInt("Enter Roll Number: ");
            Student s = service.getRecordByRollNo(rollNo);
            if (s != null) {
                System.out.println("\n=========== GRADE CARD ===========");
                System.out.println("Roll Number : " + s.getRollNo());
                System.out.println("Role / Info : " + s.getRoleDescription());
                System.out.println("Name        : " + s.getName());
                System.out.println("Age         : " + s.getAge());
                System.out.println("Marks       : [" + s.getMarks1() + ", " + s.getMarks2() + ", " + s.getMarks3() + ", " + s.getMarks4() + "]");
                System.out.println("Total Marks : " + s.calculateTotal() + " / 400.0");
                System.out.printf("Average     : %.2f%%%n", s.calculateAverage());
                System.out.println("Grade       : " + s.calculateGrade());
                System.out.println("==================================");
            } else {
                System.out.println("Student with Roll No " + rollNo + " not found.");
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void printHeader() {
        System.out.println(String.format("%-8s | %-16s | %-5s | %-6s | %-6s | %-6s | %-6s | %-7s | %s",
                "RollNo", "Name", "Age", "M1", "M2", "M3", "M4", "Avg", "Grade"));
        System.out.println("---------------------------------------------------------------------------------");
    }

    private static void printRow(Student s) {
        System.out.println(String.format("%-8d | %-16s | %-5d | %-6.1f | %-6.1f | %-6.1f | %-6.1f | %-7.1f | %s",
                s.getRollNo(), s.getName(), s.getAge(),
                s.getMarks1(), s.getMarks2(), s.getMarks3(), s.getMarks4(),
                s.calculateAverage(), s.calculateGrade()));
    }
}
