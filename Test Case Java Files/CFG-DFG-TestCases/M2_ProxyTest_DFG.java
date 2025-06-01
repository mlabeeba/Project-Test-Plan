package com.liskovsoft.smartyoutubetv2.common.proxy;

import org.junit.Test;
import java.net.SocketAddress;
import static org.junit.Assert.*;

public class M2_ProxyTest_DFG {

    // Helper method
    private PasswdInetSocketAddress addr(String host, int port) {
        return PasswdInetSocketAddress.createUnresolved(host, port, "user", "pass");
    }

    /* TC1: Check if obj is null or not a Proxy
     * Target Node: Node 2
     */
    @Test
    public void testCase1() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr("localhost", 8080));
        boolean result = proxy.equals(null);
        assertFalse(result);
    }

    /* TC2: Make sure casting obj to Proxy works
     * Target Node: Node 3
     */
    @Test
    public void testCase2() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr("localhost", 8080));
        boolean result = proxy.equals("not a proxy");
        assertFalse(result);
    }

    /* TC3: Test if both proxies have the same type
     * Target Node: Node 4
     */
    @Test
    public void testCase3() {
        Proxy proxy1 = Proxy.NO_PROXY;
        Proxy proxy2 = Proxy.NO_PROXY;
        boolean result = proxy1.equals(proxy2);
        assertTrue(result);
    }

    /* TC4: Check if p.address() is null
     * Target Node: Node 5
     */
    @Test
    public void testCase4() {
        Proxy proxy1 = Proxy.NO_PROXY;
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, addr("localhost", 8080));
        boolean result = proxy1.equals(proxy2);
        assertFalse(result);
    }

    /* TC5: Confirm this.address() is null
     * Target Node: Node 6
     */
    @Test
    public void testCase5() {
        Proxy proxy1 = Proxy.NO_PROXY; // this.address() = null
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, addr("localhost", 8080));
        boolean result = proxy1.equals(proxy2);
        assertFalse(result); /* should return false as address() == null */
    }


    /* TC6: Test when both addresses are not null and equal
     * Target Node: Node 7
     */
    @Test
    public void testCase6() {
        SocketAddress shared = addr("localhost", 8080);
        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, shared);
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, shared);
        boolean result = proxy1.equals(proxy2);
        assertTrue(result);
    }

    /* TC7: Test when both addresses are not equal
     * Target Node: Node 8
     */
    @Test
    public void testCase7() {
        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, addr("localhost", 8080));
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, addr("127.0.0.1", 8080));
        boolean result = proxy1.equals(proxy2);
        assertFalse(result);
    }

}
