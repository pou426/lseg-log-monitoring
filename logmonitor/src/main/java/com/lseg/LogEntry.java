package com.lseg;

import java.time.LocalTime;

/**
 * Represents an individual entry in a log file.
 *
 * @param time the time of the log entry
 * @param description the description text of the log entry
 * @param event the event type (e.g., START or END)
 * @param pid the process ID associated with the log entry
 */
public record LogEntry(LocalTime time, String description, String event, String pid) {
    /**
     * Returns a new instance of LogEntry from a CSV line.
     */
    public static LogEntry fromCSV(String line) {
        String[] parts = line.split(",", 4);
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid log entry: " + line);
        }
        return new LogEntry(
                LocalTime.parse(parts[0]),
                parts[1].trim(),
                parts[2].trim(),
                parts[3].trim());
    }

    /**
     * Returns a unique key for this log entry based on its description and pid.
     */
    public String getKey() {
        return String.format("%s-%s", description, pid);
    }

    /**
     * Returns a new LogEntry that marks the end of this job with the given {@code time}.
     */
    public LogEntry toEnd(LocalTime time) {
        return new LogEntry(time, description, "END", pid);
    }
}
