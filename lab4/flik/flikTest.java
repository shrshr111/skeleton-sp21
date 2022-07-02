package flik;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class flikTest {
    @Test
    public void testequal() {
        assertTrue(Flik.isSameNumber(129,129));
        assertFalse(Flik.isSameNumber(128,0));
    }
}
