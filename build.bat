@echo off
REM Complaint Management System - Build Script for Windows

echo Building Complaint Management System...
if not exist bin mkdir bin
javac -source 1.8 -target 1.8 -d bin src\*.java

if %ERRORLEVEL% EQU 0 (
    echo Build successful! Run the application with: java -cp bin CGClient
) else (
    echo Build failed!
    exit /b 1
)
