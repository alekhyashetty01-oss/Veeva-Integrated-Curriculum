# Student Record Management System (OOP Architecture)

This project implements **Case Study 4** with an enterprise-grade, clean **Object-Oriented Programming (OOP)** architecture in Java.

---

## 🏛️ OOP Concepts Implemented

1. **Encapsulation:**
   - All state variables in `Person` and `Student` are strictly `private`.
   - Access and mutations happen via getters and setters.
   - File handling logic is encapsulated inside `FileStudentRepository`.

2. **Inheritance:**
   - `Person` is an abstract base class holding common human properties (`name`, `age`).
   - `Student extends Person`, inheriting personal data while introducing academic attributes (`rollNo`, `marks1`, `marks2`, `marks3`, `marks4`).

3. **Abstraction:**
   - Interface `Gradable`: Defines contracts for grade and mark calculations (`calculateTotal()`, `calculateAverage()`, `calculateGrade()`).
   - Interface `StudentRepository`: Defines contracts for persistence CRUD operations independent of storage mechanism.
   - Interface `StudentService`: Defines the business service boundary.
   - Abstract method `getRoleDescription()` in `Person`.

4. **Polymorphism:**
   - **Method Overriding:** `Student` provides concrete implementations of `calculateGrade()`, `calculateAverage()`, and `getRoleDescription()`.
   - **Subtype Polymorphism & Dependency Inversion:** `StudentService` depends on the `StudentRepository` interface, allowing plug-and-play persistence (e.g., swapping file storage for a database without changing business logic).

---

## 📁 Package & Directory Structure

```text
StudentManagementSystem_OOP/
├── src/
│   └── com/
│       └── studentapp/
│           ├── model/
│           │   ├── Person.java            # Abstract base class (Inheritance)
│           │   ├── Gradable.java          # Interface defining grading contracts
│           │   └── Student.java           # Concrete entity (extends Person, implements Gradable)
│           ├── repository/
│           │   ├── StudentRepository.java # DAO Interface (Abstraction)
│           │   └── FileStudentRepository.java # File I/O Implementation (Encapsulation)
│           ├── service/
│           │   ├── StudentService.java    # Service Interface
│           │   └── StudentServiceImpl.java# Business Logic Implementation
│           ├── util/
│           │   └── ConsoleReader.java     # CLI input handling utility
│           └── Main.java                  # Application Entrypoint & Menu Driver
├── student.txt                            # File storage
└── README.md
```

---

## 🚀 How to Compile & Run

### 1. Compile all Java files:
```bash
javac -d bin src/com/studentapp/model/*.java src/com/studentapp/repository/*.java src/com/studentapp/service/*.java src/com/studentapp/util/*.java src/com/studentapp/Main.java
```

### 2. Run the application:
```bash
java -cp bin com.studentapp.Main
```
