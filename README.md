# LSEG Coding Challenge: Log Monitoring Application

The **LSEG Log Monitoring Application** is a Java-based application designed to process log 
files, measures how long each job takes from start to finish and generates warnings or errors 
if the processing time exceeds certain thresholds.

**Output**:
See [logmonitor/src/job-duration-report.txt](logmonitor/src/job-duration-report.txt) for example output based on given [input log file](logmonitor/src/main/resources/logs.txt).

**Entry point**: [App.java](logmonitor/src/main/java/com/lseg/App.java)

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

To run tests:

1. Go tto the _Testing sidebar_ (icon with a beaker/flask)
1. Click _Run Tests_, or run them individually via the play icon beside each test
1. Alternatively, right-click in a test file and select _Run Test_ or _Debug Test_

## Further Work

1. Improve test coverage:
    - Integration tests (e.g. for I/Os)
    - End-to-end tests
    - Smoke tests, etc
1. Allow users to specific input and output file locations via a flag.
1. Use dependency injection frameworks to manage dependencies (e.g. [`ReportGenerator`](/logmonitor/src/main/java/com/lseg/ReportGenerator.java)).