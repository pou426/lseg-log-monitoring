# LSEG Coding Challenge: Log Monitoring Application

The **LSEG Log Monitoring Application** is a Java-based application designed to process log 
files, measures how long each job takes from start to finish and generates warnings or errors 
if the processing time exceeds certain thresholds.

**Output**:
See [logmonitor/src/job-duration-report.txt](logmonitor/src/job-duration-report.txt) for example output based on given [input log file](logmonitor/src/main/resources/logs.txt).

See [**Further Work**](#further-work) section for some examples of remaining work for this application.

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

## Further Work

1. Improve test coverage: integration tests (especially for I/Os), end-to-end tests, smoke tests, etc.
1. Allow users to specific input and output file locations via a flag.