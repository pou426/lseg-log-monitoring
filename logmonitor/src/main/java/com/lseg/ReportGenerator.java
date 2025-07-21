package com.lseg;

/** Generators log monitoring report. */
public interface ReportGenerator {
    /**
     * Creates the report for a given job run entry.
     */
    public String createReportEntry(JobRun jobRun);
}
