package com.liskovsoft.smartyoutubetv2.common.proxy;

import org.junit.Test;
import static org.junit.Assert.*;
import java.net.SocketAddress;

public class M2_ProxyTest_CFG {

    // Helper method 
    private static PasswdInetSocketAddress createAddress(String host, int port, String user, String pass) {
        return PasswdInetSocketAddress.createUnresolved(host, port, user, pass);
    }

    /**
     * Path: T1 = {1, 2}
     * Tests when obj is null or not a Proxy
     * Should return false at node 2
     */
    @Test
    public void testPath1() {
        Proxy proxy = Proxy.NO_PROXY;

        assertFalse(proxy.equals(null));          // null test
        assertFalse(proxy.equals("NotAProxy"));   // type mismatch test
    }

    /**
     * Path: T2 = {1, 3, 4, 8}
     * Tests when obj is a Proxy but type does not match
     * Should return false at node 8
     */
    @Test
    public void testPath2() {
        SocketAddress addr = createAddress("localhost", 8080, "user", "pass");
        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, addr);
        Proxy proxy2 = new Proxy(Proxy.Type.SOCKS, addr);

        assertFalse(proxy1.equals(proxy2)); // type mismatch
    }

    /**
     * Path: T3 = {1, 3, 4, 5, 6}
     * Tests when both proxies are NO_PROXY with null addresses
     * Should return true at node 6
     */
    @Test
    public void testPath3() {
        Proxy proxy1 = Proxy.NO_PROXY;
        Proxy proxy2 = Proxy.NO_PROXY;

        assertTrue(proxy1.equals(proxy2)); // both type == DIRECT, address == null
    }

    /**
     * Path: T4 = {1, 3, 4, 5, 7}
     * Tests when types and addresses match
     * Should return true at node 7
     */
    @Test
    public void testPath4() {
        SocketAddress addr = createAddress("localhost", 8080, "user", "pass");
        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, addr);
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, addr);

        assertTrue(proxy1.equals(proxy2)); // type + address match
    }

    /**
     * Path: T5 = {1, 3, 4, 5, 7}
     * Tests when types match but addresses differ
     * Should return false at node 7
     */
    @Test
    public void testPath5() {
        SocketAddress addr1 = createAddress("localhost", 8080, "user1", "pass1");
        SocketAddress addr2 = createAddress("localhost", 8080, "user2", "pass2");

        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, addr1);
        Proxy proxy2 = new Proxy(Proxy.Type.HTTP, addr2);

        assertFalse(proxy1.equals(proxy2)); // same type, different address
    }
}
