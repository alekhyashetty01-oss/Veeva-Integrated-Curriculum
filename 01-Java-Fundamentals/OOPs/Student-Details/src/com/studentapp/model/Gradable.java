package com.studentapp.model;

/**
 * Interface defining the grading contract.
 */
public interface Gradable {
    double calculateTotal();
    double calculateAverage();
    String calculateGrade();
}
