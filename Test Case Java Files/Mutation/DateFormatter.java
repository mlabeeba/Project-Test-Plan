import com.stfalcon.chatkit.utils.DateFormatter;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

public class DateFormatterTest {

    @Test
    public void testFormat_validDateAndFormat() {
        Date date = new Date(0); // Jan 1, 1970
        String result = DateFormatter.format(date, "yyyy-MM-dd");

        assertEquals("1970-01-01", result);
    }

    @Test
    public void testFormat_nullDateReturnsEmptyString() {
        assertEquals("", DateFormatter.format(null, "yyyy-MM-dd"));
    }

    @Test
    public void testFormat_localeAffectsOutput() {
        // Locale-sensitive formatting
        Date date = new Date(0); // Jan 1, 1970
        String result = DateFormatter.format(date, "MMMM");

        assertNotNull(result); // Will vary by system locale
    }

    @Test
    public void testIsSameDay_sameDate() {
        Date now = new Date();
        assertTrue(DateFormatter.isSameDay(now, new Date(now.getTime())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsSameDay_firstDateNull() {
        DateFormatter.isSameDay(null, new Date());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsSameDay_secondDateNull() {
        DateFormatter.isSameDay(new Date(), null);
    }

    @Test
    public void testIsSameDay_differentDatesSameDay() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2024, Calendar.JANUARY, 5, 10, 30, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2024, Calendar.JANUARY, 5, 23, 59, 59);

        assertTrue(DateFormatter.isSameDay(cal1.getTime(), cal2.getTime()));
    }

    @Test
    public void testIsSameDay_differentDays() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2024, Calendar.JANUARY, 5);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2024, Calendar.JANUARY, 6);

        assertFalse(DateFormatter.isSameDay(cal1.getTime(), cal2.getTime()));
    }
}
