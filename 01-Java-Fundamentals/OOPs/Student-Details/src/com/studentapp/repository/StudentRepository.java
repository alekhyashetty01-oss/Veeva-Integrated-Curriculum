package com.studentapp.repository;

import com.studentapp.model.Student;
import java.util.List;

/**
 * Data Access Layer Interface adhering to Dependency Inversion Principle.
 */
public interface StudentRepository {
    boolean save(Student student);
    List<Student> findAll();
    Student findByRollNo(int rollNo);
    List<Student> findByName(String name);
    boolean deleteByRollNo(int rollNo);
    boolean update(int rollNo, String newName, int newAge);
}
