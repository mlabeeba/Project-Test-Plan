package com.liskovsoft.smartyoutubetv2.common.utils;

import static org.junit.Assert.*;
import org.junit.Test;

public class M1_CopyOnWriteHashListTest_CFG {
	
	/**
     * Path: T1 = {1, 2, 3}
     * Tests when item is null
     * Should return false and stop at node 3
     */
    @Test
    public void testPath1() {
        CopyOnWriteHashList<String> list = new CopyOnWriteHashList<>();
        boolean result = list.add(null);
        assertFalse(result); // Expect false since null items are rejected
    }

    /**
     * Path: T2 = {1, 2, 4, 6}
     * Tests when item is not in the list
     * Should skip removal and go to add directly
     */
    @Test
    public void testPath2() {
        CopyOnWriteHashList<String> list = new CopyOnWriteHashList<>();
        boolean result = list.add("newItem");
        assertTrue(result); // Expect true since it's a new item and gets added
    }

    /**
     * Path: T3 = {1, 2, 4, 5, 6}
     * Tests when the item is already in the list, but not at the last index
     * Should remove it first, then add it again
     */ 
    @Test
    public void testPath3() {
        CopyOnWriteHashList<String> list = new CopyOnWriteHashList<>();
        list.add("item");   // Add "item" at index 0
        list.add("item2");  // Add a second item to make "item" not the last element

        boolean result = list.add("item"); // Triggers remove() and re-add
        assertTrue(result);
    }
}


