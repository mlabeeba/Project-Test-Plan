package com.liskovsoft.smartyoutubetv2.common.proxy;

import org.junit.Test;
import static org.junit.Assert.*;

public class M1_PasswdURITest_DFG {

    /* TC1: Full URI with username and password → normal case
     * Target Node: Node 5–6 (use of split2, result)
     */
    @Test
    public void testCase1() throws Exception {
        PasswdURI uri = new PasswdURI("http://user:pass@host.com");
        String username = uri.getUsername();
        assertEquals("user", username);
    }

    /* TC2: No user info before @ → split length = 1
     * Target Node: Node 2
     */
    @Test
    public void testCase2() throws Exception {
        PasswdURI uri = new PasswdURI("http://host.com");
        String username = uri.getUsername();
        assertNull(username);
    }

    /* TC3: Only one part before @ → split2 length != 2
     * Target Node: Node 4
     */
    @Test
    public void testCase3() throws Exception {
        PasswdURI uri = new PasswdURI("http://useronly@host.com");
        String username = uri.getUsername();
        assertNull(username); // split2.length == 1
    }

    /* TC4: Extra colon in user info → split2.length > 2
     * Target Node: Node 4
     */
    @Test
    public void testCase4() throws Exception {
        PasswdURI uri = new PasswdURI("http://user:pass:extra@host.com");
        String username = uri.getUsername();
        assertNull(username); // split2.length == 3
    }

    /* TC5: No colon at all → split2 not defined
     * Target Node: Node 3 (split2 never created)
     */
    @Test
    public void testCase5() throws Exception {
        PasswdURI uri = new PasswdURI("http://userpass@host.com");
        String username = uri.getUsername();
        assertNull(username);
    }
}
