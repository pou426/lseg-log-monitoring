package com.lseg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

/**
 * Log Monitoring Application.
 */
public final class App {
    private static final String OUTPUT_FILENAME = "/tmp/job-duration-report.txt";

    private final JobRunCollector collector;
    private final ReportGenerator generator;

    private App(JobRunCollector collector, ReportGenerator generator) {
        this.collector = collector;
        this.generator = generator;
    }

    private List<JobRun> getJobRuns() {
        InputStream is = App.class.getClassLoader().getResourceAsStream("logs.txt");
        if (is == null) {
            throw new IllegalStateException("Unable to find logs.txt in resources");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                collector.collect(line);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read logs: " + e.getMessage(), e);
        }

        return collector.getJobRuns();
    }

    /** Runs the log monitoring application. */
    public void run() {
        List<JobRun> jobRuns = getJobRuns();
        if (jobRuns.isEmpty()) {
            System.err.println("WARNING: No job runs found. Output will not be generated.");
        }

        Path outputPath = Path.of(OUTPUT_FILENAME);
        try {
            Files.createDirectories(outputPath.getParent());

            // Warning if overwriting an existing file
            if (Files.exists(outputPath)) {
                System.err.println("WARNING: The file " + outputPath + " already exists and will be overwritten.");
            }

            try (BufferedWriter writer = Files.newBufferedWriter(
                outputPath,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE)) {
                for (JobRun jobRun : jobRuns) {
                    Optional<String> line = generator.createReportEntry(jobRun);
                    if (line.isPresent()) {
                        writer.write(line.get());
                    }
                }
                writer.write("--- End of job run report ---\n");
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write to file: " + e.getMessage(), e);
        }
    }

    /**
     * Entry point to the log monitoring application.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        JobRunCollector collector = new JobRunCollector();
        ReportGeneratorImpl generator = new ReportGeneratorImpl();
        new App(collector, generator).run();
    }
}
