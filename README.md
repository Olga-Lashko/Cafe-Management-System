# Cafe Management System GUI

## Overview
The Cafe Management System is a Java Swing application designed to streamline the process of managing a cafe. It allows users to make orders, display invoices, and manage the menu through an intuitive graphical user interface. This project was developed as part of the CITC-1311 Programming II course.

## Features
- **Main Menu**: Central hub for navigating to other parts of the application.
- **Order Menu**: Allows users to place orders, calculates taxes and totals, and generates an invoice.
- **Show Menu**: Displays the current menu items.
- **Manage Menu**: Provides options to add, change, and delete menu items.
- **Data Validation**: Ensures all input data is correct, providing error messages when necessary.
- **Persistent Storage**: Reads from and writes data to text files, preserving information between sessions.
- **Image Display**: Includes images in at least one of the menus for a richer user experience.

## Requirements
- Java Runtime Environment (JRE) 8 or higher

## Project Structure
- **Data Storage**:
  - `menu_drink.txt` - Contains all drink items and their prices.
  - `menu_food.txt` - Contains all food items and their prices.
  - `last_order.txt` - Records details of the last order made.
- **Java Classes**:
  - `CafeMSApp` - Main application class.
  - `MainMenu`, `OrderMenu`, `ShowMenu`, `ManageMenu` - GUI classes for different menus.
  - `Item` - Represents a menu item.
  - `IO` - Handles reading from and writing to text files.
  - `Console` - Manages console input (used internally).

## Running the Application

### Download JAR File
1. Go to the JavaCafeMS(https://github.com/Olga-Lashko/JavaGUI).
2. Download the `CafeMSApp.jar` file.

### On Windows
1. **Install Java Runtime Environment**:
   - Ensure the JRE is installed. Download it from the [Oracle website](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) or [OpenJDK](https://openjdk.java.net/).
2. **Open Command Prompt**:
   - Press `Win + R`, type `cmd`, and press Enter.
3. **Navigate to the JAR File Directory**:
   - Use the `cd` command to navigate to the directory where the JAR file is located:
     ```sh
     cd path\to\your\jar\file
     ```
4. **Run the JAR File**:
   - Execute the following command:
     ```sh
     java -jar CafeMSApp.jar
     ```

### On macOS
1. **Install Java Runtime Environment**:
   - Ensure the JRE is installed. Download it from the [Oracle website](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) or [OpenJDK](https://openjdk.java.net/).
2. **Open Terminal**:
   - You can find Terminal in `Applications > Utilities > Terminal`.
3. **Navigate to the JAR File Directory**:
   - Use the `cd` command to navigate to the directory where the JAR file is located:
     ```sh
     cd path/to/your/jar/file
     ```
4. **Run the JAR File**:
   - Execute the following command:
     ```sh
     java -jar CafeMSApp.jar
     ```

## Repository Contents
- `src/`: Source code files.
- `dist/`: Distribution files, including the executable JAR file.
- `README.md`: This file.
- `menu_drink.txt`: Sample data file for drinks.
- `menu_food.txt`: Sample data file for food.
- `last_order.txt`: Sample data file for the last order.

## GitHub Repository
View the source code and additional documentation on GitHub: [GitHub Repository Link](https://github.com/Olga-Lashko/JavaGUI)

## Author
- **Olga Lashko**
- 11-08-23

