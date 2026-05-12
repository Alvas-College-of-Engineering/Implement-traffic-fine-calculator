# Simple Traffic Fine Management System

A robust, Java application designed to manage traffic fines and vehicle records efficiently. This system provides a comprehensive Command Line Interface (CLI) for administrators to track, issue, and resolve traffic violations.

## 🌟 Features

* **Fine Management**:
  * Issue new fines with predefined violation types (Speeding, Red Light, Illegal Parking, No Helmet).
  * Process fine payments and track payment status (Paid/Unpaid).
  * View all fines, or filter by payment status.
  * Delete fine records.
* **Vehicle & Driver Management**:
  * Add new vehicles to the system.
  * Edit existing vehicle information (Owner name, Vehicle make).
  * Remove vehicles (only if they have no unpaid fines).
  * Automatic driver removal from the vehicle record once fines are paid.
* **Search & Analytics**:
  * Search fines specific to a vehicle registration number.
  * View comprehensive system statistics (Total revenue, unpaid amounts, payment rates, and the most common violations).

## 🛠️ Technologies Used

* **Java (Core Java)**: The entire application is built using standard Java.
* **Collections Framework**: Utilizes `ArrayList` and `HashMap` for in-memory data storage and statistics calculation.
* **Scanner API**: For processing user input via the command line.

## 🚀 How to Run the Project

### Prerequisites
Make sure you have [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) installed on your machine.

### Compilation
Open your terminal/command prompt, navigate to the project directory, and compile the Java file:
```bash
javac SimpleTrafficFineSystem.java
```

### Execution
Run the compiled Java program:
```bash
java SimpleTrafficFineSystem
```

## 🏗️ Project Structure

The project is encapsulated within a single file (`SimpleTrafficFineSystem.java`) containing three main classes:

1. **`SimpleTrafficFineSystem`**: The main class that handles the CLI menu, user interactions, and core logic for the system.
2. **`Vehicle`**: A class representing a vehicle, storing its registration number, make, and owner details.
3. **`TrafficFine`**: A class representing a fine, storing the fine number, associated vehicle, violation type, amount, location, and payment status.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!
Feel free to check the issues page if you want to contribute.

## 📝 License

This project is open-source and available under the [MIT License](LICENSE).
