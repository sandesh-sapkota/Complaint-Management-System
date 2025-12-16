#!/bin/bash
# Complaint Management System - Build Script

echo "Building Complaint Management System..."
mkdir -p bin
javac -source 1.8 -target 1.8 -d bin src/*.java

if [ $? -eq 0 ]; then
    echo "Build successful! Run the application with: java -cp bin CGClient"
else
    echo "Build failed!"
    exit 1
fi
