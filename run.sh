#!/bin/bash

# Navigate to the project directory
cd "$(dirname "$0")/logmonitor" || exit 1

# Build and package the application using Maven
mvn clean package -q > /dev/null 2>&1 || { echo "Build failed"; exit 1; }

# Run the packaged application
java -jar target/logmonitor-1.0-SNAPSHOT.jar
