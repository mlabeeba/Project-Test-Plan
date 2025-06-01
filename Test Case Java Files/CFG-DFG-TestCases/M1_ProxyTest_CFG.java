package com.liskovsoft.smartyoutubetv2.common.proxy;

import static org.junit.Assert.*;
import org.junit.Test;

import java.net.SocketAddress;

public class M1_ProxyTest_CFG {

    // Test Path 1: {1, 2}
    // obj is null → returns false
    @Test
    public void testPath1_NullObject() {
        Proxy proxy = Proxy.NO_PROXY;
        assertFalse(proxy.equals(null));
    }

    // Test Path 2: {1, 2}
    // obj is not a Proxy → returns false
    @Test
    public void testPath2_WrongType() {
        Proxy proxy = Proxy.NO_PROXY;
        assertFalse(proxy.equals("NotAProxy"));
    }

    // Test Path 3: {1, 3, 4, 5, 6}
    // Same type, both addresses are null → returns true
    @Test
    public void testPath3_SameType_BothAddressesNull() {
        Proxy p1 = Proxy.NO_PROXY;
        Proxy p2 = Proxy.NO_PROXY;
        assertTrue(p1.equals(p2));
    }

    // Test Path 4: {1, 3, 4, 5, 6}
    // Same type, one address is null → returns false
    @Test
    public void testPath4_SameType_OneAddressNull() {
        Proxy p1 = Proxy.NO_PROXY;
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        Proxy p2 = new Proxy(Proxy.Type.HTTP, sa);
        assertFalse(p1.equals(p2));
    }

    // Test Path 5: {1, 3, 4, 5, 7}
    // Same type, same address → returns true
    @Test
    public void testPath5_SameType_SameAddress() {
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        Proxy p1 = new Proxy(Proxy.Type.HTTP, sa);
        Proxy p2 = new Proxy(Proxy.Type.HTTP, sa);
        assertTrue(p1.equals(p2));
    }

    // Test Path 6: {1, 3, 4, 5, 7}
    // Same type, different address → returns false
    @Test
    public void testPath6_SameType_DifferentAddress() {
        SocketAddress sa1 = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user1", "pass1");
        SocketAddress sa2 = PasswdInetSocketAddress.createUnresolved("localhost", 8081, "user2", "pass2");
        Proxy p1 = new Proxy(Proxy.Type.HTTP, sa1);
        Proxy p2 = new Proxy(Proxy.Type.HTTP, sa2);
        assertFalse(p1.equals(p2));
    }

    // Test Path 7: {1, 3, 4, 8}
    // Different types → returns false
    @Test
    public void testPath7_DifferentTypes() {
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        Proxy p1 = new Proxy(Proxy.Type.HTTP, sa);
        Proxy p2 = new Proxy(Proxy.Type.SOCKS, sa);
        assertFalse(p1.equals(p2));
    }
}
