package com.lseg;

import java.util.Optional;

/** Generators log monitoring report. */
public interface ReportGenerator {
    /**
     * Creates warning or error report for a given job run entry,
     * if the job duration exceeds threshold.
     */
    Optional<String> createReportEntry(JobRun jobRun);
}
