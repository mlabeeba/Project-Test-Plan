package ISP3;

import org.junit.Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatterTest {

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    // --- Tests for isSameDay() ---

    @Test
    public void testIsSameDay_SameDay() throws ParseException {
        Date date1 = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse("01-01-2025");
        Date date2 = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse("01-01-2025");

        assertTrue(DateFormatter.isSameDay(date1, date2));
    }

    @Test
    public void testIsSameDay_DifferentDays() throws ParseException {
        Date date1 = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse("01-01-2025");
        Date date2 = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse("02-01-2025");

        assertFalse(DateFormatter.isSameDay(date1, date2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsSameDay_NullFirstDate() {
        DateFormatter.isSameDay(null, new Date());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsSameDay_NullSecondDate() {
        DateFormatter.isSameDay(new Date(), null);
    }

    // --- Tests for format() ---

    @Test
    public void testFormat_ValidDateAndFormat() throws ParseException {
        Date date = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse("01-01-2025");
        String formattedDate = DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH_YEAR);
        
        assertEquals("1 January 2025", formattedDate);
    }

    @Test
    public void testFormat_ValidDateAndDifferentFormats() throws ParseException {
        Date date = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse("15-08-2025");

        assertEquals("15 August 2025", DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH_YEAR));
        assertEquals("15 August", DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH));
        assertEquals("00:00", DateFormatter.format(date, DateFormatter.Template.TIME)); // Since only date is provided
    }

    @Test
    public void testFormat_NullDate() {
        String formattedDate = DateFormatter.format(null, DateFormatter.Template.STRING_DAY_MONTH_YEAR);
        assertEquals("", formattedDate);
    }


    @Test
    public void testFormat_EmptyFormatString() {
        Date date = new Date();
        String formattedDate = DateFormatter.format(date, "");
        assertNotNull(formattedDate); // Should not be null, may be a default formatted string
    }
}
