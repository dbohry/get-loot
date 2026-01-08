package mods;

import com.lhamacorp.minecraft.plugins.java.getloot.mods.GetLootFromKill;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetLootFromKillTest {

    private GetLootFromKill handler;

    @Before
    public void setup() {
        handler = new GetLootFromKill();
    }

    @Test
    public void shouldImplementListenerInterface() {
        assertTrue(org.bukkit.event.Listener.class.isAssignableFrom(GetLootFromKill.class));
    }

    @Test
    public void shouldHaveEventHandlerAnnotation() {
        try {
            java.lang.reflect.Method onKillMethod = GetLootFromKill.class.getDeclaredMethod("onKill",
                    org.bukkit.event.entity.EntityDeathEvent.class);
            assertNotNull(onKillMethod);
            assertTrue(onKillMethod.isAnnotationPresent(org.bukkit.event.EventHandler.class));
        } catch (NoSuchMethodException e) {
            fail("onKill method should exist: " + e.getMessage());
        }
    }

    @Test
    public void shouldInstantiateSuccessfully() {
        assertNotNull(handler);
    }

    @Test
    public void shouldNotThrowExceptionOnInstantiation() {
        try {
            GetLootFromKill newHandler = new GetLootFromKill();
            assertNotNull(newHandler);
        } catch (Exception e) {
            fail("Handler instantiation should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void shouldHaveExpectedMethodSignatures() {
        try {
            java.lang.reflect.Method onKillMethod = GetLootFromKill.class.getDeclaredMethod("onKill",
                    org.bukkit.event.entity.EntityDeathEvent.class);
            assertNotNull(onKillMethod);
            assertEquals(void.class, onKillMethod.getReturnType());
        } catch (NoSuchMethodException e) {
            fail("Expected method not found: " + e.getMessage());
        }
    }

    @Test
    public void shouldHaveValidConstructor() {
        try {
            java.lang.reflect.Constructor<GetLootFromKill> constructor =
                    GetLootFromKill.class.getDeclaredConstructor();
            assertNotNull(constructor);
            assertTrue(java.lang.reflect.Modifier.isPublic(constructor.getModifiers()));
        } catch (NoSuchMethodException e) {
            fail("Public no-args constructor should exist: " + e.getMessage());
        }
    }
}