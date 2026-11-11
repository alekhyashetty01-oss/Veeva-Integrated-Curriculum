package com.studentapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final String filePath;

    public StudentService(String filePath) {
        this.filePath = filePath;
        initFile();
    }

    private void initFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error initializing file: " + e.getMessage());
        }
    }
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return students;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromFileString(line);
                if (s != null) {
                    students.add(s);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading students: " + e.getMessage());
        }
        return students;
    }
    private void saveAll(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Student s : students) {
                bw.write(s.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public boolean addStudent(Student student) {
        if (findStudentByRollNo(student.getRollNo()) != null) {
            return false; // Roll number already exists
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(student.toFileString());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error saving record: " + e.getMessage());
            return false;
        }
    }
    public Student findStudentByRollNo(int rollNo) {
        List<Student> students = getAllStudents();
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                return s;
            }
        }
        return null;
    }
    public List<Student> findStudentsByName(String name) {
        List<Student> result = new ArrayList<>();
        List<Student> students = getAllStudents();
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name) || s.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }
    public boolean deleteStudentByRollNo(int rollNo) {
        List<Student> students = getAllStudents();
        boolean removed = students.removeIf(s -> s.getRollNo() == rollNo);
        if (removed) {
            saveAll(students);
            return true;
        }
        return false;
    }
    public boolean updateStudent(int rollNo, String newName, int newAge) {
        List<Student> students = getAllStudents();
        boolean updated = false;
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                s.setName(newName);
                s.setAge(newAge);
                updated = true;
                break;
            }
        }
        if (updated) {
            saveAll(students);
            return true;
        }
        return false;
    }
}
