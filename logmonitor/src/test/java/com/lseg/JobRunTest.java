package com.lseg;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the JobRun class.
 */
public class JobRunTest {

    @Test
    void testJobRun() {
        LogEntry start = new LogEntry(LocalTime.of(11, 36, 11),
            "scheduled task 796",
            "START",
            "57672");
        LogEntry end = new LogEntry(LocalTime.of(12, 36, 11),
            "scheduled task 796",
            "END",
            "57672");
        JobRun expected = new JobRun(
            start, Optional.of(end), Duration.ofHours(1L));
        JobRun result = JobRun.of(start, end);
        assertEquals(expected, result);
    }

    @Test
    void testJobRun_unfinishedJob() {
        LogEntry start = new LogEntry(LocalTime.of(11, 36, 11),
            "scheduled task 796",
            "START",
            "57672");
        LogEntry end = new LogEntry(LocalTime.of(12, 36, 11),
            "scheduled task 796",
            "END",
            "57672");
        JobRun expected = new JobRun(
            start, Optional.empty(), Duration.ofHours(1L));
        JobRun result = JobRun.of(start, end, false);
        assertEquals(expected, result);
    }

    @Test
    void testJobRun_invalidStartAndEndTimes() {
        LogEntry start = new LogEntry(LocalTime.of(12, 36, 11),
            "scheduled task 796",
            "START",
            "57672");
        LogEntry end = new LogEntry(LocalTime.of(11, 36, 11),
            "scheduled task 796",
            "END",
            "57672");
        assertThrows(IllegalArgumentException.class, () -> JobRun.of(start, end));
    }
}
