package com.studentapp.repository;

import com.studentapp.model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates all flat file I/O operations for student.txt
 */
public class FileStudentRepository implements StudentRepository {
    private final String filePath;

    public FileStudentRepository(String filePath) {
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
            System.err.println("File initialization error: " + e.getMessage());
        }
    }

    @Override
    public synchronized List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student s = Student.fromRecordString(line);
                if (s != null) {
                    list.add(s);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    private synchronized void persistAll(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Student s : students) {
                bw.write(s.toRecordString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public synchronized boolean save(Student student) {
        if (findByRollNo(student.getRollNo()) != null) {
            return false;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(student.toRecordString());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error appending record: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Student findByRollNo(int rollNo) {
        for (Student s : findAll()) {
            if (s.getRollNo() == rollNo) {
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Student> findByName(String name) {
        List<Student> matched = new ArrayList<>();
        for (Student s : findAll()) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                matched.add(s);
            }
        }
        return matched;
    }

    @Override
    public synchronized boolean deleteByRollNo(int rollNo) {
        List<Student> students = findAll();
        boolean removed = students.removeIf(s -> s.getRollNo() == rollNo);
        if (removed) {
            persistAll(students);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean update(int rollNo, String newName, int newAge) {
        List<Student> students = findAll();
        boolean found = false;
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                s.setName(newName);
                s.setAge(newAge);
                found = true;
                break;
            }
        }
        if (found) {
            persistAll(students);
            return true;
        }
        return false;
    }
}
