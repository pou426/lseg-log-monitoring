package com.lseg;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the JobRunCollector class.
 */
public class JobRunCollectorTest {
    @Test
    void getJobRuns_singleJob() {
        JobRunCollector collector = new JobRunCollector();
        collector.collect("12:00:00,Job A,START,1234");
        collector.collect("12:01:00,Job A,END,1234");
        List<JobRun> results = collector.getJobRuns();

        assertEquals(1, results.size());

        LogEntry expectedStartEntry = new LogEntry(
            LocalTime.of(12, 00, 00), "Job A", "START", "1234");
        LogEntry expectedEndEntry = new LogEntry(
            LocalTime.of(12, 01, 00), "Job A", "END", "1234");
        assertEquals(
            new JobRun(expectedStartEntry, Optional.of(expectedEndEntry), Duration.ofMinutes(1L)),
            results.get(0));
    }

    @Test
    void getJobRuns_multipleJobs() {
        // TODO(pou426): Implement this test.
    }

    @Test
    void getJobRuns_sortedByStartTime() {
        // TODO(pou426): Implement this test.
    }

    @Test
    void getJobRuns_unfinishedJobs() {
        // TODO(pou426): Implement this test.
    }

    @Test
    void getJobRuns_resultsAreFlushed() {
        // TODO(pou426): Implement this test.
    }

    @Test
    void getJobRuns_missingStartEvent_ignored() {
        // TODO(pou426): Implement this test.
    }

    @Test
    void getJobRuns_unknownEvent_ignored() {
        // TODO(pou426): Implement this test.
    }
}
