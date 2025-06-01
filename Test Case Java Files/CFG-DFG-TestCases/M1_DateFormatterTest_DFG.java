package main.java.com.stfalcon.chatkit.utils;


import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;

public class M1_DateFormatterTest_DFG {

    /* TC1: date is null → early return
     * Target Node: Node 1
     */
    @Test
    public void testCase1() {
        String result = DateFormatter.format(null, "dd/MM/yyyy");
        assertEquals("", result); // Should return empty string
    }

    /* TC2: valid date and format → normal path
     * Target Node: Node 3 (uses both date and format)
     */
    @Test
    public void testCase2() {
        Date date = new Date(0); // Jan 1, 1970
        String format = "yyyy";
        String expected = new SimpleDateFormat(format).format(date);

        String result = DateFormatter.format(date, format);
        assertEquals(expected, result); // Should match expected formatting
    }
}
