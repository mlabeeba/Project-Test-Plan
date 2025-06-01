package main.java.com.stfalcon.chatkit.utils;

import static org.junit.Assert.*;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class M1_DateFormatterTest_CFG {

    // Test Path 1: {1, 2}
    @Test
    public void testPath1() {
        String result = DateFormatter.format(null, "yyyy-MM-dd");
        assertEquals("", result);
    }

    // Test Path 2: {1, 3}
    @Test
    public void testPath2() {
        Date date = new Date(0); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.getDefault());
        String expected = sdf.format(date);  
        String result = DateFormatter.format(date, "yyyy");
        assertEquals(expected, result); 
    }



}
