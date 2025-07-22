package com.lseg;

import java.time.Duration;
import java.util.Optional;

/**
 * Represents a completed job run.
 *
 * @param start the log entry marking the job's start
 * @param end the optional log entry marking the job's end
 * @param duration the total time between start and end
 */
public record JobRun(LogEntry start, Optional<LogEntry> end, Duration duration) {
    public static JobRun of(LogEntry start, LogEntry end) {
        return JobRun.of(start, end, true);
    }

    /**
     * Returns a new JobRun with the given start and end times, where {@code isFinished}
     * indicates whether the job is finished.
     *
     * Note that if a job is unfinished, the end time is the latest timestamp seen from
     * logs, which is only used for computing the current duration of the job.
     */
    public static JobRun of(LogEntry start, LogEntry end, boolean isFinished) {
        if (end.time().isBefore(start.time())) {
            throw new IllegalArgumentException("end time must be after start time");
        }

        Optional<LogEntry> optEnd = Optional.ofNullable(isFinished ? end : null);
        Duration duration = Duration.between(start.time(), end.time());
        return new JobRun(start, optEnd, duration);
    }
}
