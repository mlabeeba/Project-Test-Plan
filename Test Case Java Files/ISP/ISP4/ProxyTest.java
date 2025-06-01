package ISP3;

import org.junit.Test;

import static org.junit.Assert.*;
import java.net.SocketAddress;

/**
 * Unit tests for the Proxy class to verify constructor and equals() method behavior
 * based on Input Space Partitioning (ISP) testing.
 */
public class ProxyTest{

    /**
     * Test case: Valid HTTP Proxy with a valid PasswdInetSocketAddress
     * Expected result: Successful creation of Proxy instance
     */
    @Test
    public void testProxyConstructor_validHTTPProxy() {
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, sa);
        assertEquals(Proxy.Type.HTTP, proxy.type());
        assertEquals(sa, proxy.address());
    }

    /**
     * Test case: Valid SOCKS Proxy with a valid PasswdInetSocketAddress
     * Expected result: Successful creation of Proxy instance
     */
    @Test
    public void testProxyConstructor_validSOCKSProxy() {
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 1080, "user", "pass");
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, sa);
        assertEquals(Proxy.Type.SOCKS, proxy.type());
        assertEquals(sa, proxy.address());
    }

    /**
     * Test case: Invalid Direct Proxy with a non-null SocketAddress
     * Expected result: IllegalArgumentException
     */
    @Test
    public void testProxyConstructor_invalidDirectProxy() {
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        assertThrows(IllegalArgumentException.class, () -> new Proxy(Proxy.Type.DIRECT, sa));
    }

    /**
     * Test case: Comparing two Proxy objects with same type and same address
     * Expected result: true
     */
    @Test
    public void testEquals_sameProxyObjects() {
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        Proxy p1 = new Proxy(Proxy.Type.HTTP, sa);
        Proxy p2 = new Proxy(Proxy.Type.HTTP, sa);
        assertTrue(p1.equals(p2));
    }
    
    /**
     * Test case: Passing null as Proxy.Type should still construct Proxy
     * since there is no null check and condition evaluates safely.
     */
    @Test
    public void testProxyConstructor_nullTypeAllowed() {
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        Proxy proxy = new Proxy(null, sa);
        assertNull(proxy.type());
        assertEquals(sa, proxy.address());
    }

    /**
     * Test case: Comparing two Proxy objects with different types
     * Expected result: false
     */
    @Test
    public void testEquals_differentType() {
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        Proxy p1 = new Proxy(Proxy.Type.HTTP, sa);
        Proxy p2 = new Proxy(Proxy.Type.SOCKS, sa);
        assertFalse(p1.equals(p2));
    }

    /**
     * Test case: Comparing two Proxy objects with same type but different addresses
     * Expected result: false
     */
    @Test
    public void testEquals_differentSocketAddress() {
        SocketAddress sa1 = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        SocketAddress sa2 = PasswdInetSocketAddress.createUnresolved("localhost", 9090, "user", "pass");
        Proxy p1 = new Proxy(Proxy.Type.HTTP, sa1);
        Proxy p2 = new Proxy(Proxy.Type.HTTP, sa2);
        assertFalse(p1.equals(p2));
    }

    /**
     * Test case: Comparing a Proxy object to null
     * Expected result: false
     */
    @Test
    public void testEquals_withNullObject() {
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        Proxy p1 = new Proxy(Proxy.Type.HTTP, sa);
        assertFalse(p1.equals(null));
    }

    /**
     * Test case: Comparing a Proxy object to an unrelated object type
     * Expected result: false
     */
    @Test
    public void testEquals_withNonProxyObject() {
        SocketAddress sa = PasswdInetSocketAddress.createUnresolved("localhost", 8080, "user", "pass");
        Proxy p1 = new Proxy(Proxy.Type.HTTP, sa);
        Object obj = new Object();
        assertFalse(p1.equals(obj));
    }
}
