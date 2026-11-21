# Case Study: Student Data Management System Using Java File Handling

## Problem Statement

Develop a simple console-based Java application to manage student information using File Handling concepts. The application should store student records permanently in a text file named `student.txt`.

At the beginning of the application, display a File Management menu that allows the user to create a new file, use an existing file, or exit the application.

If the user selects the Create New File option, the application should create a new `student.txt` file if it does not already exist. If the file already exists, display an appropriate message without overwriting the existing data.

If the user selects the Use Existing File option, the application should verify whether the file exists. If it exists, allow the user to access the Student Data Management menu. Otherwise, display a message indicating that the file does not exist.

## Student Attributes

Each student record should contain the following information:

1. Roll Number
2. Name
3. Age
4. Marks 1
5. Marks 2
6. Marks 3
7. Marks 4
8. Grade (calculated automatically based on the average of four subject marks)

## Functional Requirements

The application should provide the following operations through a menu-driven interface:

**1. Add Student Record**

Allow the user to enter student details, including roll number, name, age, and marks in four subjects. Calculate the average marks and automatically assign a grade. Store the student record in `student.txt`. Prevent duplicate roll numbers.

**2. Read All Student Records**

Read and display all student records stored in `student.txt`. If no records are available, display an appropriate message.

**3. Read Student Record by Roll Number**

Allow the user to enter a roll number and search for the corresponding student record. Display the complete student details if found.

**4. Read Student Record by Name**

Allow the user to search for a student using their name. Display the matching student details.

**5. Delete Student Record by Roll Number**

Allow the user to enter a roll number and delete the corresponding student record from the file. If the record is not found, display an appropriate message.

**6. Update Student Record**

Allow the user to update the following student information using the roll number:

- Roll Number
- Name
- Age

Save the updated information back into the file without affecting other student records.

**7. Exit**

Allow the user to exit the application safely.

## Technical Requirements

- Use Core Java and File Handling concepts.
- Use the `File` class to create and verify the existence of the file.
- Use `FileWriter` to write student records.
- Use `FileReader` and `BufferedReader` to read student records.
- Use a temporary file to perform update and delete operations.
- Use `Scanner` to accept user input.
- Use methods, loops, conditional statements, and switch statements to organize the application.

## Expected Outcome

The application should provide a simple and efficient way to manage student information through a console-based menu. All student records should be stored permanently in `student.txt`, allowing users to add, view, search, update, and delete student records even after closing and reopening the application.

The main objective of this case study is to understand and implement Java File Handling operations such as file creation, reading, writing, searching, updating, and deleting data.
