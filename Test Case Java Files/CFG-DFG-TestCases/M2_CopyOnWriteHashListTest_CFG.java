package com.liskovsoft.smartyoutubetv2.common.utils;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class M2_CopyOnWriteHashListTest_CFG {

    private CopyOnWriteHashList<String> list;

    @Before
    public void setUp() {
        list = new CopyOnWriteHashList<>();
    }

    // Test Path 1: 1 → 2 → 3
    // item is null → return without adding
    @Test
    public void testPath1() {
        list.add(0, null);
        assertEquals(0, list.size());
    }

    // Test Path 2: 1 → 2 → 3
    // item is at last index → return without adding
    @Test
    public void testPath2() {
        list.add("A");
        list.add(0, "A");
        assertEquals(1, list.size());
    }

    // Test Path 3: 1 → 2 → 4 → 5 → 6 → 7
    // item exists, not last → remove then add at index
    @Test
    public void testPath3() {
        list.add("B");
        list.add("C");
        list.add(1, "B");
        assertEquals("B", list.get(1));
    }

    // Test Path 4: 1 → 2 → 4 → 6 → 7
    // new item at valid index → add at index
    @Test
    public void testPath4() {
        list.add("X");
        list.add("Y");
        list.add(1, "Z");
        assertEquals("Z", list.get(1));
    }

    // Test Path 5: 1 → 2 → 4 → 6 → 8
    // new item at invalid index → add at end
    @Test
    public void testPath5() {
        list.add("M");
        list.add(99, "N");
        assertEquals("N", list.get(list.size() - 1));
    }
}
