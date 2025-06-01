import com.liskovsoft.smartyoutubetv2.common.proxy.PasswdURI;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class PasswdURITest {

    @Test
    public void testGetUsernameAndPassword_normal() throws URISyntaxException {
        PasswdURI uri = new PasswdURI("http://user123:pass123@127.0.0.1:8080");

        assertEquals("user123", uri.getUsername());
        assertEquals("pass123", uri.getPassword());
    }

    @Test
    public void testGetUsernameAndPassword_missingColon() throws URISyntaxException {
        PasswdURI uri = new PasswdURI("http://user123@127.0.0.1:8080");

        assertNull(uri.getUsername());
        assertNull(uri.getPassword());
    }

    @Test
    public void testGetUsernameAndPassword_missingAtSymbol() throws URISyntaxException {
        PasswdURI uri = new PasswdURI("http://127.0.0.1:8080");

        assertNull(uri.getUsername());
        assertNull(uri.getPassword());
    }

    @Test
    public void testGetUsernameAndPassword_colonButNoPassword() throws URISyntaxException {
        PasswdURI uri = new PasswdURI("http://user123:@127.0.0.1:8080");

        assertEquals("user123", uri.getUsername());
        assertEquals("", uri.getPassword()); // still valid: user: empty password
    }

    @Test
    public void testGetUsernameAndPassword_emptyAuthority() throws URISyntaxException {
        PasswdURI uri = new PasswdURI("http://@127.0.0.1:8080");

        assertNull(uri.getUsername());
        assertNull(uri.getPassword());
    }

    @Test
    public void testGetUsernameAndPassword_extraColonsInCredentials() throws URISyntaxException {
        PasswdURI uri = new PasswdURI("http://user:extra:pass@127.0.0.1:8080");

        // split2.length != 2 → should be null
        assertNull(uri.getUsername());
        assertNull(uri.getPassword());
    }
}
