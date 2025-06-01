package ISP2;

import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class PasswdURITest {

    private PasswdURI uri;

    // --- Valid URI Tests ---

    @Test
    public void testValidURIWithSchemeHostPortAndCredentials() throws URISyntaxException {
        uri = new PasswdURI("http://user:pass@proxy.example.com:8080");

        assertEquals("http", uri.getScheme());
        assertEquals("proxy.example.com", uri.getHost());
        assertEquals(8080, uri.getPort());
        assertEquals("user", uri.getUsername());
        assertEquals("pass", uri.getPassword());
    }

    @Test
    public void testValidURIWithSchemeAndHostOnly() throws URISyntaxException {
        uri = new PasswdURI("http://proxy.example.com");

        assertEquals("http", uri.getScheme());
        assertEquals("proxy.example.com", uri.getHost());
        assertEquals(-1, uri.getPort());
        assertNull(uri.getUsername());
        assertNull(uri.getPassword());
    }

    @Test
    public void testValidURIWithUserOnly() throws URISyntaxException {
        uri = new PasswdURI("http://user@proxy.example.com:8080");

        assertEquals("http", uri.getScheme());
        assertEquals("proxy.example.com", uri.getHost());
        assertEquals(8080, uri.getPort());
        assertNull(uri.getUsername());
        assertNull(uri.getPassword());
    }

    @Test
    public void testValidURIWithMalformedUserInfo() throws URISyntaxException {
        uri = new PasswdURI("http://user:pass:extra@proxy.example.com:8080");

        assertEquals("http", uri.getScheme());
        assertEquals("proxy.example.com", uri.getHost());
        assertEquals(8080, uri.getPort());
        assertNull(uri.getUsername());
        assertNull(uri.getPassword());
    }

    @Test
    public void testValidURINoPort() throws URISyntaxException {
        uri = new PasswdURI("http://user:pass@proxy.example.com");

        assertEquals("http", uri.getScheme());
        assertEquals("proxy.example.com", uri.getHost());
        assertEquals(-1, uri.getPort());
        assertEquals("user", uri.getUsername());
        assertEquals("pass", uri.getPassword());
    }

    @Test
    public void testURINoScheme() throws URISyntaxException {
        uri = new PasswdURI("//user:pass@proxy.example.com:8080");

        assertNull(uri.getScheme());
        assertEquals("proxy.example.com", uri.getHost());
        assertEquals(8080, uri.getPort());
        assertEquals("user", uri.getUsername());
        assertEquals("pass", uri.getPassword());
    }


    @Test
    public void testNoUserInfoButValidHostAndPort() throws URISyntaxException {
        uri = new PasswdURI("http://proxy.example.com:8080");

        assertEquals("http", uri.getScheme());
        assertEquals("proxy.example.com", uri.getHost());
        assertEquals(8080, uri.getPort());
        assertNull(uri.getUsername());
        assertNull(uri.getPassword());
    }

    // --- Invalid URI Tests ---

    @Test(expected = URISyntaxException.class)
    public void testInvalidURISyntax() throws URISyntaxException {
        new PasswdURI("ht^tp://[invalid-uri]");
    }

    @Test(expected = URISyntaxException.class)
    public void testMalformedURIMissingScheme() throws URISyntaxException {
        new PasswdURI("user:pass@proxy.example.com:8080");
    }

    @Test(expected = URISyntaxException.class)
    public void testMalformedURIMissingHost() throws URISyntaxException {
        new PasswdURI("http://user:pass@:8080");
    }

    @Test(expected = URISyntaxException.class)
    public void testMalformedURIMissingEverything() throws URISyntaxException {
        new PasswdURI("");
    }

    @Test(expected = URISyntaxException.class)
    public void testInvalidCharactersInURI() throws URISyntaxException {
        new PasswdURI("http://user:pa$$@proxy.example.com:8080");
    }
}
