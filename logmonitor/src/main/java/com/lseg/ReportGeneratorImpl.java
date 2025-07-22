package com.lseg;

/** Implements the ReportGenerator interface. */
public class ReportGeneratorImpl implements ReportGenerator {
    private static final int TEN_MINUTES_IN_SECONDS = 600;
    private static final int FIVE_MINUTES_IN_SECONDS = 300;
    private static final String UNFINISHED = "UNFINISHED";

    @Override
    public String createReportEntry(JobRun jobRun) {
        StringBuilder sb = new StringBuilder();

        // Log job run information.
        String jobId = getJobId(jobRun);
        sb.append(String.format("[INFO] Job: %s   Start: %s   End: %s   Duration: %s\n",
                jobId,
                jobRun.start().time(),
                jobRun.end().isPresent() ? jobRun.end().get().time() : UNFINISHED,
                getDurationString(jobRun)));

        // Alert if the log run duration exceeds threshold.
        long seconds = jobRun.duration().toSeconds();
        if (seconds > TEN_MINUTES_IN_SECONDS) {
            sb.append(String.format("[ERROR] %s took more than 10 minutes\n", jobId));
        } else if (seconds > FIVE_MINUTES_IN_SECONDS) {
            sb.append(String.format("[WARNING] %s took more than 5 minutes\n", jobId));
        }

        return sb.toString();
    }

    private static String getJobId(JobRun jobRun) {
        return String.format("%s (pid=%s)", jobRun.start().description(), jobRun.start().pid());
    }

    private static String getDurationString(JobRun jobRun) {
        long seconds = jobRun.duration().toSeconds();
        return jobRun.end().isPresent() ? String.format("%02d:%02d", seconds / 60, seconds % 60) : UNFINISHED;
    }
}
