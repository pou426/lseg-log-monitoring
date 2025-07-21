package com.lseg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;

/**
 * Log Monitoring Application.
 */
public final class App {
    private final JobRunCollector collector;

    private App(JobRunCollector collector) {
        this.collector = collector;
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

        System.out.println("Job runs:");
        for (JobRun jobRun : jobRuns) {
            System.out.println(jobRun);
        }
    }

    /**
     * Entry point to the log monitoring application.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        JobRunCollector collector = new JobRunCollector();
        new App(collector).run();
    }
}
