package com.liskovsoft.smartyoutubetv2.common.proxy;

import org.junit.Test;
import static org.junit.Assert.*;

public class M2_PasswdURITest_DFG {

    /* TC1: Full URI with username and password, normal case
     * Target Node: Node 5–6 (uses split2[1] to set result)
     */
    @Test
    public void testCase1() throws Exception {
        PasswdURI uri = new PasswdURI("http://user:pass@host.com");
        String password = uri.getPassword();
        assertEquals("pass", password);
    }

    /* TC2: No user info before @  split.length == 1
     * Target Node: Node 2
     */
    @Test
    public void testCase2() throws Exception {
        PasswdURI uri = new PasswdURI("http://host.com");
        String password = uri.getPassword();
        assertNull(password);
    }

    /* TC3: Only username present, split2.length == 1
     * Target Node: Node 4
     */
    @Test
    public void testCase3() throws Exception {
        PasswdURI uri = new PasswdURI("http://user@host.com");
        String password = uri.getPassword();
        assertNull(password);
    }

    /* TC4: Extra colon in userinfo, split2.length > 2
     * Target Node: Node 4
     */
    @Test
    public void testCase4() throws Exception {
        PasswdURI uri = new PasswdURI("http://user:pass:extra@host.com");
        String password = uri.getPassword();
        assertNull(password);
    }

    /* TC5: Malformed user info without colon
     * Target Node: Node 3 only
     */
    @Test
    public void testCase5() throws Exception {
        PasswdURI uri = new PasswdURI("http://userpass@host.com");
        String password = uri.getPassword();
        assertNull(password);
    }
}
