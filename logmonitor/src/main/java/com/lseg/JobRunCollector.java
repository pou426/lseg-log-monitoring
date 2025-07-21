package com.lseg;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * Collects job runs based on given log entries. When the collect job runs are
 * retrieved, the collected data are flushed from the collector.
 * 
 * <p>Important:
 *      1. The collector is not thread-safe. It must be used in a single thread.
 *      2. The collector assumes log entries are provided in chronological order.
 * </p>
*/
public class JobRunCollector {
    private final Map<String, LogEntry> startedJobs;
    private final List<JobRun> jobRuns;
    private LocalTime latestTimeStamp;

    public JobRunCollector() {
        startedJobs = new HashMap<>();
        jobRuns = new ArrayList<>();
        latestTimeStamp = LocalTime.MIN;
    }

    /** Processes a single log entry and collects job runs information. */
    public void collect(String line) {
        LogEntry entry = LogEntry.fromCSV(line);
        collect(line, entry);
    }

    private void collect(String line, LogEntry entry) {
        latestTimeStamp = entry.time();
        switch (entry.event()) {
            case "START" -> startedJobs.put(entry.getKey(), entry);
            case "END" -> {
                if (!startedJobs.containsKey(entry.getKey())) {
                    System.err.println("WARNING: No START event found for line: " + line);
                    break;
                }
                LogEntry startEntry = startedJobs.remove(entry.getKey());
                jobRuns.add(JobRun.of(startEntry, entry));
            }
            default -> System.err.println("WARNING: Unknown event (must be START or END) for line: " + line);
        }
    }

    /**
     * Return collected job runs and flush the collected data. Durations for unfinished jobs are computed based on the latest timestamp.
     */
    public List<JobRun> getJobRuns() {
        for (LogEntry unfinishedJob : startedJobs.values()) {
            jobRuns.add(JobRun.of(unfinishedJob, unfinishedJob.toEnd(latestTimeStamp), /* isFinished= */ false));
        }

        List<JobRun> results = new ArrayList<>(jobRuns);
        flush();
        return results;
    }

    private void flush() {
        startedJobs.clear();
        jobRuns.clear();
        latestTimeStamp = LocalTime.MIN;
    }
}
