package com.lseg;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/** 
 * Unit test for the LogEntry class. 
 */
public class LogEntryTest {
   
    @Test
    void testLogEntry_fromCSV() {
        String line = "11:36:11,scheduled task 796,START,57672";
        LogEntry result = LogEntry.fromCSV(line);
        LogEntry expected = new LogEntry(
            LocalTime.of(11, 36, 11),
            "scheduled task 796",
            "START",
            "57672");
        assertEquals(expected, result);
    }

    @Test
    void testLogEntry_fromCSV_whiteSpacesAreTrimmed() {
        String line = "11:36:11, scheduled task 796 , START , 57672 ";
        LogEntry result = LogEntry.fromCSV(line);
        LogEntry expected = new LogEntry(
            LocalTime.of(11, 36, 11),
            "scheduled task 796",
            "START",
            "57672");
        assertEquals(expected, result);
    }

    @Test
    void testLogEntry_fromCSV_invalidEntry_throwsException() {
        String line = "11:36:11,scheduled task 796,START";
        assertThrows(IllegalArgumentException.class, () -> LogEntry.fromCSV(line));
    }

    @Test
    void testLogEntry_getKey() {
        LogEntry entry = new LogEntry(
            LocalTime.of(11, 36, 11),
            "scheduled task 796",
            "START",
            "57672"
        );
        assertEquals("scheduled task 796-57672", entry.getKey());
    }

    @Test
    void testLogEntry_withTimeAndEvent() {
        LogEntry entry = new LogEntry(
            LocalTime.of(11, 36, 11),
            "scheduled task 796",
            "START",
            "57672"
        );
        LogEntry expected = new LogEntry(
            LocalTime.of(01, 01, 01),
            "scheduled task 796",
            "END",
            "57672"
        );

        assertEquals(expected, entry.toEnd(LocalTime.of(01, 01,01)));
    }
}
