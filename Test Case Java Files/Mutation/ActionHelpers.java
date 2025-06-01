import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class HelpersMutationTest {

    @Test
    public void testAppendArray_fullMerge() {
        Integer[] first = {1, 2};
        Integer[] second = {3, 4};
        Integer[] expected = {1, 2, 3, 4};
        Integer[] result = Helpers.appendArray(first, second);
        assertArrayEquals("Should merge both arrays fully", expected, result);
    }

    @Test
    public void testAppendArray_firstEmpty() {
        String[] first = {};
        String[] second = {"a", "b"};
        String[] expected = {"a", "b"};
        String[] result = Helpers.appendArray(first, second);
        assertArrayEquals("First empty, should equal second", expected, result);
    }

    @Test
    public void testAppendArray_secondEmpty() {
        String[] first = {"x", "y"};
        String[] second = {};
        String[] expected = {"x", "y"};
        String[] result = Helpers.appendArray(first, second);
        assertArrayEquals("Second empty, should equal first", expected, result);
    }

    @Test
    public void testAppendArray_bothEmpty() {
        Double[] first = {};
        Double[] second = {};
        Double[] expected = {};
        Double[] result = Helpers.appendArray(first, second);
        assertArrayEquals("Both empty, should return empty array", expected, result);
    }

    // Mutant killers: Wrong offset, wrong length, wrong copy
    @Test
    public void testAppendArray_preservesOrderAndContents() {
        Character[] first = {'a', 'b'};
        Character[] second = {'c', 'd'};
        Character[] result = Helpers.appendArray(first, second);
        assertEquals('a', result[0].charValue());
        assertEquals('b', result[1].charValue());
        assertEquals('c', result[2].charValue());
        assertEquals('d', result[3].charValue());
    }

    // ----------- appendStream() Tests -------------

    @Test
    public void testAppendStream_combinesContent() throws IOException {
        InputStream first = new ByteArrayInputStream("Hello ".getBytes());
        InputStream second = new ByteArrayInputStream("World".getBytes());
        InputStream result = Helpers.appendStream(first, second);

        byte[] bytes = result.readAllBytes();
        String output = new String(bytes);
        assertEquals("Hello World", output);
    }

    @Test
    public void testAppendStream_firstEmpty() throws IOException {
        InputStream first = new ByteArrayInputStream(new byte[0]);
        InputStream second = new ByteArrayInputStream("World".getBytes());
        InputStream result = Helpers.appendStream(first, second);

        assertEquals("World", new String(result.readAllBytes()));
    }

    @Test
    public void testAppendStream_secondEmpty() throws IOException {
        InputStream first = new ByteArrayInputStream("Hello".getBytes());
        InputStream second = new ByteArrayInputStream(new byte[0]);
        InputStream result = Helpers.appendStream(first, second);

        assertEquals("Hello", new String(result.readAllBytes()));
    }

    @Test
    public void testAppendStream_bothEmpty() throws IOException {
        InputStream first = new ByteArrayInputStream(new byte[0]);
        InputStream second = new ByteArrayInputStream(new byte[0]);
        InputStream result = Helpers.appendStream(first, second);

        assertEquals("", new String(result.readAllBytes()));
    }
}

