import com.liskovsoft.smartyoutubetv2.common.proxy.Proxy;
import com.liskovsoft.smartyoutubetv2.common.proxy.PasswdInetSocketAddress;
import org.junit.Test;

import java.net.InetSocketAddress;

import static org.junit.Assert.*;

public class ProxyTest {

    @Test
    public void testEquals_sameTypeAndAddress_returnsTrue() {
        PasswdInetSocketAddress addr1 = new PasswdInetSocketAddress("127.0.0.1", 8080);
        Proxy p1 = new Proxy(Proxy.Type.HTTP, addr1);
        Proxy p2 = new Proxy(Proxy.Type.HTTP, new PasswdInetSocketAddress("127.0.0.1", 8080));

        assertTrue(p1.equals(p2));
    }

    @Test
    public void testEquals_sameTypeDifferentAddress_returnsFalse() {
        Proxy p1 = new Proxy(Proxy.Type.HTTP, new PasswdInetSocketAddress("127.0.0.1", 8080));
        Proxy p2 = new Proxy(Proxy.Type.HTTP, new PasswdInetSocketAddress("127.0.0.1", 9090));

        assertFalse(p1.equals(p2));
    }

    @Test
    public void testEquals_differentTypeSameAddress_returnsFalse() {
        PasswdInetSocketAddress addr = new PasswdInetSocketAddress("127.0.0.1", 8080);
        Proxy p1 = new Proxy(Proxy.Type.HTTP, addr);
        Proxy p2 = new Proxy(Proxy.Type.SOCKS, addr);

        assertFalse(p1.equals(p2));
    }

    @Test
    public void testEquals_nullObject_returnsFalse() {
        Proxy p = new Proxy(Proxy.Type.HTTP, new PasswdInetSocketAddress("127.0.0.1", 8080));
        assertFalse(p.equals(null));
    }

    @Test
    public void testEquals_differentObjectType_returnsFalse() {
        Proxy p = new Proxy(Proxy.Type.HTTP, new PasswdInetSocketAddress("127.0.0.1", 8080));
        assertFalse(p.equals("NotAProxy"));
    }

    @Test
    public void testEquals_bothWithNullAddress_returnsTrue() {
        Proxy p1 = Proxy.NO_PROXY;
        Proxy p2 = Proxy.NO_PROXY;
        assertTrue(p1.equals(p2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_directWithAddress_throwsException() {
        new Proxy(Proxy.Type.DIRECT, new PasswdInetSocketAddress("127.0.0.1", 8080));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_nonPasswdInetSocketAddress_throwsException() {
        new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 80));
    }

    @Test
    public void testHashCode_withAddress() {
        Proxy p = new Proxy(Proxy.Type.HTTP, new PasswdInetSocketAddress("127.0.0.1", 8080));
        int expected = Proxy.Type.HTTP.hashCode() + p.address().hashCode();
        assertEquals(expected, p.hashCode());
    }

    @Test
    public void testHashCode_noAddress() {
        Proxy p = Proxy.NO_PROXY;
        assertEquals(Proxy.Type.DIRECT.hashCode(), p.hashCode());
    }
}
