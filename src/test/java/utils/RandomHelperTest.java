package utils;

import com.lhamacorp.minecraft.plugins.java.getloot.utils.RandomHelper;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomHelperTest {

    @Test
    public void shouldGenerateRandomInRange() {
        Integer result = RandomHelper.random(10, 1);

        assertNotNull(result);
        assertTrue(result >= 1);
        assertTrue(result <= 10);
    }

    @Test
    public void shouldGenerateRandomInDefaultRange() {
        Integer result = RandomHelper.random();

        assertNotNull(result);
        assertTrue(result >= 1);
        assertTrue(result <= 2000);
    }

    @Test
    public void shouldHandleMinimalRange() {
        Integer result = RandomHelper.random(2, 1);

        assertNotNull(result);
        assertTrue(result == 1 || result == 2);
    }

    @Test
    public void shouldThrowExceptionWhenMinEqualsMax() {
        try {
            RandomHelper.random(5, 5);
            fail("Expected IllegalArgumentException when min equals max");
        } catch (IllegalArgumentException e) {
            assertEquals("max must be greater than min", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWhenMinGreaterThanMax() {
        try {
            RandomHelper.random(1, 10);
            fail("Expected IllegalArgumentException when min > max");
        } catch (IllegalArgumentException e) {
            assertEquals("max must be greater than min", e.getMessage());
        }
    }

    @Test
    public void shouldRespectParameterOrder() {
        Integer result = RandomHelper.random(100, 50);

        assertNotNull(result);
        assertTrue(result >= 50);
        assertTrue(result <= 100);

        Integer result2 = RandomHelper.random(2, 1);
        assertTrue(result2 == 1 || result2 == 2);
    }
}