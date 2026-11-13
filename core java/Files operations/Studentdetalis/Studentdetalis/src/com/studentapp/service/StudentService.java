package com.studentapp.service;

import com.studentapp.model.Student;
import java.util.List;

public interface StudentService {
    boolean addStudentRecord(Student student);
    List<Student> getAllRecords();
    Student getRecordByRollNo(int rollNo);
    List<Student> getRecordsByName(String name);
    boolean deleteRecordByRollNo(int rollNo);
    boolean updateStudentRecord(int rollNo, String newName, int newAge);
}
