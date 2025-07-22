package com.lseg;

import java.util.Optional;

/** Implements the ReportGenerator interface. */
public class ReportGeneratorImpl implements ReportGenerator {
    private static final int TEN_MINUTES_IN_SECONDS = 600;
    private static final int FIVE_MINUTES_IN_SECONDS = 300;
    private static final String UNFINISHED = "UNFINISHED";

    @Override
    public Optional<String> createReportEntry(JobRun jobRun) {
        String jobId = getJobId(jobRun);
        long seconds = jobRun.duration().toSeconds();

        if (seconds > TEN_MINUTES_IN_SECONDS) {
            return Optional.of(String.format("[ERROR] %s took more than 10 minutes (start=%s, duration=%s)\n", jobId, jobRun.start().time(), getDurationString(jobRun)));
        }

        if (seconds > FIVE_MINUTES_IN_SECONDS) {
            return Optional.of(String.format("[WARNING] %s took more than 5 minutes (start=%s, duration=%s)\n", jobId, jobRun.start().time(), getDurationString(jobRun)));
        }

        return Optional.empty();
    }

    private static String getJobId(JobRun jobRun) {
        return String.format("%s (pid=%s)", jobRun.start().description(), jobRun.start().pid());
    }

    private static String getDurationString(JobRun jobRun) {
        long seconds = jobRun.duration().toSeconds();
        return jobRun.end().isPresent() ? String.format("%02d:%02d", seconds / 60, seconds % 60) : UNFINISHED;
    }
}
