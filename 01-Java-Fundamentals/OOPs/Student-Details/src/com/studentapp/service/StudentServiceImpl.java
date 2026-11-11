package com.studentapp.service;

import com.studentapp.model.Student;
import com.studentapp.repository.StudentRepository;
import java.util.List;

/**
 * Service implementation containing core business validation and repository delegation.
 */
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addStudentRecord(Student student) {
        if (student == null || student.getRollNo() <= 0) {
            return false;
        }
        return repository.save(student);
    }

    @Override
    public List<Student> getAllRecords() {
        return repository.findAll();
    }

    @Override
    public Student getRecordByRollNo(int rollNo) {
        return repository.findByRollNo(rollNo);
    }

    @Override
    public List<Student> getRecordsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return List.of();
        }
        return repository.findByName(name.trim());
    }

    @Override
    public boolean deleteRecordByRollNo(int rollNo) {
        return repository.deleteByRollNo(rollNo);
    }

    @Override
    public boolean updateStudentRecord(int rollNo, String newName, int newAge) {
        return repository.update(rollNo, newName, newAge);
    }
}
