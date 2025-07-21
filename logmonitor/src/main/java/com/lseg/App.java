package com.lseg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

/**
 * Log Monitoring Application.
 */
public final class App {
    private App() {
    }

    /** Runs the log monitoring application. */
    public void run() {
        InputStream is = App.class.getClassLoader().getResourceAsStream("logs.txt");
        if (is == null) {
            throw new IllegalStateException("Unable to find logs.txt in resources");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LogEntry logEntry = LogEntry.fromCSV(line);
                System.out.println(logEntry);
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read logs: " + e.getMessage(), e);
        }
    }

    /**
     * Entry point to the log monitoring application.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        new App().run();
    }
}
