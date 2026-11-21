# Case Study 4: Student Record Management System Using Java OOP and File Handling

## Problem Statement

Develop a console-based Student Record Management System using Core Java, Object-Oriented Programming (OOP) principles, and File Handling concepts.

The application should manage student information efficiently by following a clean, modular, and reusable OOP architecture. Student records should be stored permanently in a text file named `student.txt`.

The application should be designed using separate classes, interfaces, and packages to ensure proper separation of responsibilities and maintainability.

## Student Attributes

Each student record should contain the following information:

1. Roll Number
2. Name
3. Age
4. Marks 1
5. Marks 2
6. Marks 3
7. Marks 4
8. Total Marks
9. Average Marks
10. Grade (calculated automatically based on average marks)

## Functional Requirements

The application should provide the following operations through a menu-driven console interface.

**1. Create or Access Student File**

- Check whether `student.txt` exists.
- If the file does not exist, create a new file.
- If the file already exists, allow access to the existing student records.
- Do not overwrite previously stored records.

**2. Add Student Record**

- Accept student details from the user.
- Validate the student information.
- Calculate total marks and average marks.
- Automatically calculate the student's grade.
- Store the student information in `student.txt`.
- Prevent duplicate roll numbers.

**3. Read All Student Records**

- Retrieve all student records from the file.
- Display complete student information, including total marks, average, and grade.
- Display an appropriate message if no records are available.

**4. Search Student by Roll Number**

- Accept a roll number from the user.
- Search for the corresponding student record.
- Display the complete details if the record exists.

**5. Search Student by Name**

- Accept a student name from the user.
- Search for matching student records.
- Display the corresponding student information.

**6. Delete Student Record**

- Accept the student's roll number.
- Find the matching record.
- Remove the selected student record from the file.
- Preserve all other student records.

**7. Update Student Record**

Allow the user to update student information using the roll number. The application should support updating:

- Roll Number
- Name
- Age

The updated information should be saved permanently in the file.

**8. Exit**

Allow the user to exit the application safely.

## OOP Concepts to Be Implemented

**1. Encapsulation**

- Declare class variables as private.
- Provide getters and setters to access and modify student information.
- Encapsulate file handling operations inside the repository implementation.

**2. Inheritance**

- Create an abstract class `Person` containing common attributes such as name and age.
- Create a `Student` class that extends `Person`.
- Add student-specific academic attributes to the `Student` class.

**3. Abstraction**

- Create a `Gradable` interface to define methods for calculating total marks, average marks, and grade.
- Create a `StudentRepository` interface to define student data management operations.
- Create a `StudentService` interface to define business operations.
- Use the abstract method `getRoleDescription()` in the `Person` class.

**4. Polymorphism**

- Implement method overriding in the `Student` class.
- Provide implementations for grade calculation and role description.
- Use interface references to achieve runtime polymorphism.
- Allow the service layer to work with different repository implementations.

## Technical Requirements

- Use Core Java and OOP principles.
- Use an abstract class and interfaces.
- Implement inheritance and polymorphism.
- Use collections wherever required for temporary record management.
- Use Java File Handling classes such as `File`, `FileWriter`, `FileReader`, and `BufferedReader`.
- Use a temporary file for updating and deleting student records.
- Organize the application into separate packages such as model, repository, service, and util.
- Use exception handling to manage file-related errors.
- Implement a menu-driven console application.

## Expected Outcome

The application should provide a modular and reusable Student Record Management System that allows users to create, read, search, update, and delete student records.

All student information should be stored permanently in `student.txt`.

The application should demonstrate how Object-Oriented Programming concepts can be combined with Java File Handling to develop a structured and maintainable software application.

## Learning Objectives

By completing this case study, students should gain practical knowledge of:

1. Implementing the four major OOP concepts in Java.
2. Designing applications using classes and interfaces.
3. Understanding abstraction through interfaces and abstract classes.
4. Implementing inheritance and method overriding.
5. Applying encapsulation to protect data.
6. Understanding repository and service layer architecture.
7. Performing CRUD operations using Java File Handling.
8. Developing modular, reusable, and maintainable Java applications.
