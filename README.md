# Complaint Management System

A Java-based desktop application for managing and tracking complaints. Register complaints, check their status, and manage submissions through an easy-to-use admin panel.

## Features

- **Register Complaints** - Submit new complaints with department selection and detailed description
- **Check Status** - Look up complaint status by complaint number
- **Admin Panel** - View all complaints in a clean table, add solutions, and manage submissions  
- **Report View** - See a summary of all complaints
- **Data Persistence** - Complaints are saved automatically to a file

## Requirements

- Java 8 or higher

## Setup & Build

### On Linux/Mac:
```bash
git clone https://github.com/sandesh-sapkota/Complaint-Management-System.git
cd Complaint-Management-System
bash build.sh
java -cp bin CGClient
```

### On Windows:
```cmd
git clone https://github.com/sandesh-sapkota/Complaint-Management-System.git
cd Complaint-Management-System
build.bat
java -cp bin CGClient
```

### Manual Build:
```bash
mkdir bin
javac -source 1.8 -target 1.8 -d bin src/*.java
java -cp bin CGClient
```

## How to Use

1. **Register a Complaint** - Click button 1 to file a new complaint, select department, write details
2. **Check Status** - Click button 2 and enter your complaint number to see if solution is provided
3. **Admin Panel** - Click button 3, enter password (see below) to view all complaints and add solutions
4. **View Report** - Click button 4 to see all complaints in a report

## Admin Access

To access the admin panel:
- **Password:** `implementer` (hint: First letter is 'i')

## Project Structure

```
Complaint-Management-System/
├── src/
│   ├── CGClient.java        (Entry point)
│   ├── ComplaintGUI.java    (Main menu window)
│   ├── compRegister.java    (Complaint registration form)
│   ├── compStatus.java      (Status checker)
│   ├── compCheck.java       (Admin panel)
│   ├── compReport.java      (Report view)
│   ├── compFile.java        (Data persistence)
│   └── complaint.java       (Data model)
├── bin/                     (Compiled .class files)
├── build.sh                 (Build script for Linux/Mac)
├── build.bat                (Build script for Windows)
└── README.md
```

## How It Works

- Complaints are stored in a file (auto-created in system temp directory)
- Each complaint gets a unique number
- Admin can view all complaints and provide solutions
- Solutions are saved and displayed when users check their complaint status

## Notes

- No database required - works standalone with file-based storage
- Cross-platform - runs on Windows, Mac, and Linux
- Fully compiled and ready to run once built

