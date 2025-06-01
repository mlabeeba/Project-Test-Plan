package com.liskovsoft.smartyoutubetv2.common.proxy;

import org.junit.Test;
import static org.junit.Assert.*;
import java.net.SocketAddress;

public class M1_ProxyTest_DFG {

    private PasswdInetSocketAddress addr(String host, int port) {
        return PasswdInetSocketAddress.createUnresolved(host, port, "user", "pass");
    }

    /* TC1: obj is null → return false
     * Target Node: Node 1
     */
    @Test
    public void testCase1() {
        Proxy proxy = Proxy.NO_PROXY;
        assertFalse(proxy.equals(null));
    }

    /* TC2: obj is not a Proxy → return false
     * Target Node: Node 1
     */
    @Test
    public void testCase2() {
        Proxy proxy = Proxy.NO_PROXY;
        assertFalse(proxy.equals("not a proxy"));
    }

    /* TC3: Same instance → return true
     * Target Node: Node 4
     */
    @Test
    public void testCase3() {
        Proxy proxy = Proxy.NO_PROXY;
        assertTrue(proxy.equals(proxy));
    }

    /* TC4: Same type, both addresses null → return true
     * Target Node: Node 5–6
     */
    @Test
    public void testCase4() {
        Proxy proxy1 = Proxy.NO_PROXY;
        Proxy proxy2 = Proxy.NO_PROXY;
        assertTrue(proxy1.equals(proxy2));
    }

    /* TC5: Same type, one address null → return false
     * Target Node: Node 5–6
     */
    @Test
    public void testCase5() {
        Proxy proxy1 = Proxy.NO_PROXY;
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, addr("localhost", 8080));
        assertFalse(proxy1.equals(proxy2));
    }

    /* TC6: Same type, same non-null address → return true
     * Target Node: Node 7
     */
    @Test
    public void testCase6() {
        SocketAddress sa = addr("localhost", 8080);
        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, sa);
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, sa);
        assertTrue(proxy1.equals(proxy2));
    }

    /* TC7: Same type, different address → return false
     * Target Node: Node 8
     */
    @Test
    public void testCase7() {
        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, addr("localhost", 8080));
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, addr("example.com", 8080));
        assertFalse(proxy1.equals(proxy2));
    }

    /* TC8: Different type → return false regardless of address
     * Target Node: Node 4
     */
    @Test
    public void testCase8() {
        SocketAddress sa = addr("localhost", 8080);
        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, sa);
        Proxy proxy2 = new Proxy(Proxy.Type.SOCKS, sa);
        assertFalse(proxy1.equals(proxy2));
    }
}
