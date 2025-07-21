package com.lseg;

import java.time.LocalTime;

/** Represents an individual entry in a log file */
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
}