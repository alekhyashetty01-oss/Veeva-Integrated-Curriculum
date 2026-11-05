# Student Record Management System (Java File Handling)

A modular, clean, and object-oriented console application in Java that manages student academic records persisted in a flat text file (`student.txt`).

---

## 📁 Project Structure

```text
StudentManagementSystem/
├── src/
│   └── com/
│       └── studentapp/
│           ├── Student.java          # Model representing a student record and grade calculation
│           ├── StudentService.java   # File I/O service handling student.txt CRUD operations
│           └── Main.java             # Interactive 8-option console menu driver
├── student.txt                       # Pre-populated flat text file storage (delimited by '|')
└── README.md                         # Documentation & compilation instructions
```

---

## 📋 Features & Menu Options

1. **Add Record:** Validates uniqueness of roll number, captures age and 4 subject marks (0-100), and appends to `student.txt`.
2. **Read All Records:** Parses all lines from `student.txt` and formats them in an aligned tabular console output.
3. **Read Record on Roll No:** Exact record lookup by student roll number.
4. **Read Record on Name:** Substring and case-insensitive search by student name.
5. **Delete Record on Roll No:** Removes the specified roll number and synchronizes `student.txt`.
6. **Update Record on Roll No, Age, Name:** Updates name and age for the specified roll number without modifying academic marks.
7. **Grade:** Computes total, 4-subject average, and assigns academic grades:
   - `90% and above` : **A+**
   - `80% - 89%`     : **A**
   - `70% - 79%`     : **B**
   - `60% - 69%`     : **C**
   - `50% - 59%`     : **D**
   - `Below 50%`     : **F (Fail)**
8. **Exit:** Gracefully terminates the application.

---

## 🚀 How to Compile and Run

### Step 1: Open Terminal / Command Prompt
Navigate to the root directory `StudentManagementSystem/`:
```bash
cd StudentManagementSystem
```

### Step 2: Compile the Java Classes
```bash
javac -d bin src/com/studentapp/*.java
```

### Step 3: Run the Application
```bash
java -cp bin com.studentapp.Main
```
*(The program will read/write from `student.txt` in the current working directory).*
