package main.java.com.stfalcon.chatkit.utils;

import org.junit.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class M2_DateFormatterTest_DFG {

    /* TC1: cal1 is null - should throw exception
     * Target Node: Node 1
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCase1() {
        Calendar cal2 = Calendar.getInstance();
        DateFormatter.isSameDay(null, cal2);
    }

    /* TC2: cal2 is null - should throw exception
     * Target Node: Node 1
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCase2() {
        Calendar cal1 = Calendar.getInstance();
        DateFormatter.isSameDay(cal1, null);
    }

    /* TC3: both calendars represent the same day
     * Target Node: Node 5 (uses cal1 and cal2 in comparisons)
     */
    @Test
    public void testCase3() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = (Calendar) cal1.clone(); // same timestamp
        assertTrue(DateFormatter.isSameDay(cal1, cal2));
    }

    /* TC4: same year/day but different ERA
     * Target Node: Node 5
     */
    @Test
    public void testCase4() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = (Calendar) cal1.clone();
        cal2.set(Calendar.ERA, GregorianCalendar.BC);
        assertFalse(DateFormatter.isSameDay(cal1, cal2));
    }

    /* TC5: same ERA and YEAR but different DAY_OF_YEAR
     * Target Node: Node 5
     */
    @Test
    public void testCase5() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = (Calendar) cal1.clone();
        cal2.add(Calendar.DAY_OF_YEAR, 1); // next day
        assertFalse(DateFormatter.isSameDay(cal1, cal2));
    }
}

