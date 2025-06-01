package CopyOnWriteHashList;

import com.liskovsoft.smartyoutubetv2.common.utils.CopyOnWriteHashList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CopyOnWriteHashListTest {
    private CopyOnWriteHashList<String> list;

    @Before
    public void setUp() {
        list = new CopyOnWriteHashList<>();
    }

    @Test
    public void testAdd_nullItem_shouldNotBeAdded() {
        boolean result = list.add(null);
        assertFalse(result);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testAdd_uniqueItem_shouldBeAdded() {
        boolean result = list.add("Apple");
        assertTrue(result);
        assertEquals(1, list.size());
        assertEquals("Apple", list.get(0));
    }

    @Test
    public void testAdd_duplicateAtEnd_shouldNotAddAgain() {
        list.add("Banana");
        boolean result = list.add("Banana"); // should be ignored because it's at end
        assertFalse(result);
        assertEquals(1, list.size());
    }

    @Test
    public void testAdd_duplicateNotAtEnd_shouldBeMovedToEnd() {
        list.add("A");
        list.add("B");
        list.add("C");

        boolean result = list.add("B"); // should move B to end
        assertTrue(result);
        assertEquals(3, list.size());
        assertEquals("C", list.get(1));
        assertEquals("B", list.get(2)); // B now at end
    }

    @Test
    public void testAddIndex_validIndex_uniqueItem_shouldAddAtIndex() {
        list.add("X");
        list.add("Y");

        list.add(1, "Z");

        assertEquals(3, list.size());
        assertEquals("X", list.get(0));
        assertEquals("Z", list.get(1));
        assertEquals("Y", list.get(2));
    }

    @Test
    public void testAddIndex_invalidIndex_shouldAppend() {
        list.add("One");
        list.add(100, "Two"); // out of bounds, so should append

        assertEquals(2, list.size());
        assertEquals("Two", list.get(1));
    }

    @Test
    public void testAddIndex_duplicateAtSameIndex_shouldNotAddAgain() {
        list.add("First");
        list.add(0, "First"); // same index

        assertEquals(1, list.size());
        assertEquals("First", list.get(0));
    }

    @Test
    public void testAddIndex_duplicateNotAtIndex_shouldMoveToIndex() {
        list.add("One");
        list.add("Two");
        list.add(0, "Two"); // remove and insert at 0

        assertEquals(2, list.size());
        assertEquals("Two", list.get(0));
        assertEquals("One", list.get(1));
    }
}
