package com.liskovsoft.smartyoutubetv2.common.utils;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class M1_CopyOnWriteHashListTest_DFG {

    private CopyOnWriteHashList<String> list;

    @Before
    public void setUp() {
        list = new CopyOnWriteHashList<>();
    }
    
    
    /*
     * TC1: Use of 'index' in condition (index >= 0 && indexOf(item) == index)
     * Target Node: Node 2
     */
    @Test
    public void testCase1() {
        list.add("A");                	 // index = 0
        boolean result = list.add("A");  // indexOf(A) == index
        assertFalse(result);             // should return false
    }
    

    /*
     * TC2: Use of 'item' in null check
     * Target Node: Node 2
     */
    @Test
    public void testCase2() {
        boolean result = list.add(null);
        assertFalse(result);             // null should not be added
    }

    
    /*
     * TC3: Use of 'item' in contains(item) check
     * Target Node: Node 4
     */
    @Test
    public void testCase3() {
        list.add("B");
        list.add("X");        			 // push B to index 0
        boolean result = list.add("B");  // B is not last anymore
        assertTrue(result);  			 // B should be re-added
    }

    
    /*
     * TC4: Use of 'item' in remove(item)
     * Target Node: Node 5
     */
    @Test
    public void testCase4() {
        list.add("D");
        list.add("E");
        boolean result = list.add("D");  // triggers remove(D), then add(D)
        assertTrue(result);

        // Check that D appears only once and at the last position
        assertEquals("D", list.get(list.size() - 1));
        assertEquals(1, list.stream().filter(x -> x.equals("D")).count());
    }

    
    /*
     * TC5: Use of 'item' in super.add(item)
     * Target Node: Node 6
     */
    @Test
    public void testCase5() {
        boolean result = list.add("F");
        assertTrue(result);
        assertEquals("F", list.get(0));
    }

    
    /*
     * TC6: Use of 'index' and 'item' in full complex condition
     * Target Node: Node 2
     */
    @Test
    public void testCase6() {
        list.add("G");
        list.add("H");
        boolean result = list.add("G");   // index = 1, indexOf(G) = 0 → condition false
        assertTrue(result);               // allowed to add again (after removal)
    }

    
    /*
     * TC7: Add a brand-new item
     * Target Node: Node 6
     */
    @Test
    public void testCase7() {
        boolean result = list.add("I");
        assertTrue(result);               // item not in list, should be added
    }

    
    /*
     * TC8: index = -1 (list is empty)
     * Target Node: Node 2
     */
    @Test
    public void testCase8() {
        boolean result = list.add("J");
        assertTrue(result);               // index = -1, skip first condition
    }
}