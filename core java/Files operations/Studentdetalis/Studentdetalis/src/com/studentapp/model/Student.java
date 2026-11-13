package com.studentapp.model;

/**
 * Student inherits Person and implements Gradable interface.
 */
public class Student extends Person implements Gradable {
    private static final long serialVersionUID = 1L;

    private int rollNo;
    private double marks1;
    private double marks2;
    private double marks3;
    private double marks4;

    public Student(int rollNo, String name, int age, double marks1, double marks2, double marks3, double marks4) {
        super(name, age);
        this.rollNo = rollNo;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        this.marks4 = marks4;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public double getMarks1() {
        return marks1;
    }

    public void setMarks1(double marks1) {
        this.marks1 = marks1;
    }

    public double getMarks2() {
        return marks2;
    }

    public void setMarks2(double marks2) {
        this.marks2 = marks2;
    }

    public double getMarks3() {
        return marks3;
    }

    public void setMarks3(double marks3) {
        this.marks3 = marks3;
    }

    public double getMarks4() {
        return marks4;
    }

    public void setMarks4(double marks4) {
        this.marks4 = marks4;
    }

    // Polymorphic implementation of Gradable
    @Override
    public double calculateTotal() {
        return marks1 + marks2 + marks3 + marks4;
    }

    @Override
    public double calculateAverage() {
        return calculateTotal() / 4.0;
    }

    @Override
    public String calculateGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F (Fail)";
    }

    // Polymorphic implementation of Person abstract method
    @Override
    public String getRoleDescription() {
        return "Student [RollNo: " + rollNo + ", Name: " + getName() + "]";
    }

    // Convert object state to file serialization format (delimiter: |)
    public String toRecordString() {
        return rollNo + "|" + getName() + "|" + getAge() + "|" + marks1 + "|" + marks2 + "|" + marks3 + "|" + marks4;
    }

    // Factory parsing method
    public static Student fromRecordString(String line) {
        if (line == null || line.trim().isEmpty()) return null;
        String[] parts = line.split("\\|");
        if (parts.length == 7) {
            int roll = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            int age = Integer.parseInt(parts[2].trim());
            double m1 = Double.parseDouble(parts[3].trim());
            double m2 = Double.parseDouble(parts[4].trim());
            double m3 = Double.parseDouble(parts[5].trim());
            double m4 = Double.parseDouble(parts[6].trim());
            return new Student(roll, name, age, m1, m2, m3, m4);
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format(
            "Roll No: %-5d | Name: %-16s | Age: %-3d | M1: %-5.1f | M2: %-5.1f | M3: %-5.1f | M4: %-5.1f | Avg: %-5.1f | Grade: %s",
            rollNo, getName(), getAge(), marks1, marks2, marks3, marks4, calculateAverage(), calculateGrade()
        );
    }
}
