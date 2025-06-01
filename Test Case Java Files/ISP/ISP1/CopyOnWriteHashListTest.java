package ISP1;

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

    // --- Tests for boolean add(T item) ---

    @Test
    public void testAdd_NullItem_ReturnsFalse() {
        assertFalse(list.add(null));
        assertTrue(list.isEmpty());
    }

    @Test
    public void testAdd_NewItem_ReturnsTrue() {
        assertTrue(list.add("A"));
        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }

    @Test
    public void testAdd_DuplicateAtEnd_ReturnsFalse() {
        list.add("A");
        assertFalse(list.add("A")); // duplicate at end
        assertEquals(1, list.size());
    }


    @Test
    public void testAdd_NewItemToPopulatedList() {
        list.add("X");
        assertTrue(list.add("Y"));
        assertEquals(2, list.size());
        assertEquals("Y", list.get(1));
    }

    // --- Tests for void add(int index, T item) ---

    @Test
    public void testAddAtIndex_NullItem_DoesNothing() {
        list.add("A");
        list.add(0, null);
        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }

    @Test
    public void testAddAtIndex_DuplicateAtSameIndex_DoesNothing() {
        list.add("A");
        list.add(0, "A"); // same index
        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }

    @Test
    public void testAddAtIndex_DuplicateAtDifferentIndex_RemovedAndInserted() {
        list.add("A");
        list.add("B");
        list.add(0, "B"); // remove from 1, insert at 0
        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
    }

    @Test
    public void testAddAtIndex_OutOfBounds_Appends() {
        list.add("X");
        list.add(100, "Y"); // index > size
        assertEquals(2, list.size());
        assertEquals("Y", list.get(1));
    }

    @Test
    public void testAddAtIndex_NegativeIndex_Appends() {
        list.add("A");
        list.add(-1, "B");
        assertEquals(2, list.size());
        assertEquals("B", list.get(1));
    }

    @Test
    public void testAddAtIndex_ValidIndex_InsertsCorrectly() {
        list.add("A");
        list.add("C");
        list.add(1, "B");
        assertEquals(3, list.size());
        assertEquals("B", list.get(1));
    }
}
