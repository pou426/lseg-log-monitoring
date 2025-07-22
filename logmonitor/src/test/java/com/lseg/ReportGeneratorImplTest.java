package com.lseg;

import org.junit.jupiter.api.Test;

/**
 * Unit test for the ReportGeneratorImpl class.
 */
public class ReportGeneratorImplTest {
    @Test
    void createReportEntry_withinThreshold_doesNotGenerateLog() {
        // TODO(pou426): Implement this test.
    }

    @Test
    void createReportEntry_exceedsFiveMinutes_showsWarning() {
        // TODO(pou426): Implement this test.
    }

    @Test
    void createReportEntry_exceedsTenMinutes_showsError() {
        // TODO(pou426): Implement this test.
    }

    @Test
    void createReportEntry_exceedsThresholdAndUnfinished_markedAsUnfinished() {
        // TODO(pou426): Implement this test.
    }
}
