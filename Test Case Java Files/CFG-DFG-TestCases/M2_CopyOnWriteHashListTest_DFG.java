package com.liskovsoft.smartyoutubetv2.common.utils;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class M2_CopyOnWriteHashListTest_DFG {

    private CopyOnWriteHashList<String> list;

    @Before
    public void setUp() {
        list = new CopyOnWriteHashList<>();
    }

    /* TC1: item is null → skips all logic
     * Target Node: Node 2
     */
    @Test
    public void testCase1() {
        list.add(0, null);
    }

    /* TC2: item is in list at correct index → early return
     * Target Node: Node 2
     */
    @Test
    public void testCase2() {
        list.add("X");
        int index = list.indexOf("X");
        list.add(index, "X");
    }


    /* TC3: index in bounds → goes to super.add(index, item)
     * Target Node: Node 6–7
     */
    @Test
    public void testCase3() {
        list.add("Z");
        list.add(0, "A");
        assertTrue(list.contains("A"));
    }

    /* TC4: index out of bounds → goes to super.add(item)
     * Target Node: Node 8
     */
    @Test
    public void testCase4() {
        int index = list.size() + 5;
        list.add(index, "B");
        assertTrue(list.contains("B"));
    }
}
