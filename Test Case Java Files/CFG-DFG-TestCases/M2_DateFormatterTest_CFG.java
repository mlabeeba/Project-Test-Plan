package main.java.com.stfalcon.chatkit.utils;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class M2_DateFormatterTest_CFG {

    // Test Path 1: {1, 2}
    // One input is null – should throw IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testPath1() {
        DateFormatter.isSameDay(null, new Date());
    }

    // Test Path 2: {1, 3, 4, 5}
    // Both dates are on the same day – should return true
    @Test
    public void testPath2() {
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.JANUARY, 1, 10, 0, 0);
        Date date1 = cal.getTime();

        cal.set(2023, Calendar.JANUARY, 1, 23, 59, 59);
        Date date2 = cal.getTime();

        assertTrue(DateFormatter.isSameDay(date1, date2));
    }

    // Test Path 3: {1, 3, 4, 5}
    // Dates are not on the same day – should return false
    @Test
    public void testPath3() {
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.JANUARY, 1);
        Date date1 = cal.getTime();

        cal.set(2023, Calendar.JANUARY, 2);
        Date date2 = cal.getTime();

        assertFalse(DateFormatter.isSameDay(date1, date2));
    }
}
