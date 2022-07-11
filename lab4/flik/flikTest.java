package flik;
import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void testequal() {
        assertTrue(Flik.isSameNumber(100, 100));
        assertFalse(Flik.isSameNumber(128, 0));
    }
}
