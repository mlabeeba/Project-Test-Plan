package com.liskovsoft.smartyoutubetv2.common.proxy;

import static org.junit.Assert.*;
import org.junit.Test;

import java.net.URI;

public class M2_PasswdURITest_CFG {

    private PasswdURI uri;

    // Test Path 1: {1, 2, 6}
    // No '@' in authority, split.length is not 2
    @Test
    public void testPath1() throws Exception {
        uri = new PasswdURI("http://hostname");
        assertNull(uri.getPassword());
    }

    // Test Path 2: {1, 2, 3, 4, 6}
    // '@' present, but no ':' in first part
    @Test
    public void testPath2() throws Exception {
        uri = new PasswdURI("http://user@hostname");
        assertNull(uri.getPassword());
    }

    // Test Path 3: {1, 2, 3, 4, 5, 6}
    // '@' and ':' are present, valid format
    @Test
    public void testPath3() throws Exception {
        uri = new PasswdURI("http://user:pass@hostname");
        assertEquals("pass", uri.getPassword());
    }
}

