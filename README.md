# LSEG Coding Challenge: Log Monitoring Application

The **LSEG Log Monitoring Application** is a Java-based application designed to process log 
files, measures how long each job takes from start to finish and generates warnings or errors 
if the processing time exceeds certain thresholds.

## Project Structure

```text
LSEG-LOG-MONITORING/
├── logmonitor/                     # Main project directory
│   ├── src/                        # Source files
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── com/
│   │   │           └── lseg/
│   │   │               └── App.java          # Main application entry point
│   │   │               └── ...               # Other application classes
│   │   │   └── resources/
│   │   │       └── log.log                   # Input log file
│   │   └── test/
│   │       └── java/
│   │           └── com/
│   │               └── lseg/
│   │                   └── AppTest.java      # Unit tests
│   │                   └── ...               # Other test classes
│
│   └── target/                     # Compiled classes and build output
│       ├── classes/...
│       └── test-classes/...
│
├── pom.xml                         # Maven build configuration
├── .gitignore
├── .editorconfig                   # Code style configuration
├── LICENSE                         # License file
└── README.md                       # This file
```

## Development

### Running from VSCode

1. Open the folder in VSCode
1. Install the Java Extension Pack if prompted
1. Open App.java, then click _Run_