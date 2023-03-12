package config;

import com.lhamacorp.minecraft.plugins.java.getloot.config.PropertiesConfiguration;
import org.bukkit.Material;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PropertiesConfigurationTest {

    @Test
    public void should_load_default_common_currency() {
        assertEquals(2, Arrays.stream(PropertiesConfiguration.PROPERTIES
                .get("items.common-currency")
                .toString()
                .split(",")).map(Material::valueOf).toList().size());
    }

    @Test
    public void should_load_default_rare_currency() {
        assertEquals(2, Arrays.stream(PropertiesConfiguration.PROPERTIES
                .get("items.rare-currency")
                .toString()
                .split(",")).map(Material::valueOf).toList().size());
    }

    @Test
    public void should_load_default_very_common() {
        assertEquals(16, Arrays.stream(PropertiesConfiguration.PROPERTIES
                .get("items.very-common")
                .toString()
                .split(",")).map(Material::valueOf).toList().size());
    }

    @Test
    public void should_load_default_common() {
        assertEquals(16, Arrays.stream(PropertiesConfiguration.PROPERTIES
                .get("items.common")
                .toString()
                .split(",")).map(Material::valueOf).toList().size());
    }

    @Test
    public void should_load_default_rare() {
        assertEquals(18, Arrays.stream(PropertiesConfiguration.PROPERTIES
                .get("items.rare")
                .toString()
                .split(",")).map(Material::valueOf).toList().size());
    }

    @Test
    public void should_load_default_very_rare() {
        assertEquals(27, Arrays.stream(PropertiesConfiguration.PROPERTIES
                .get("items.very-rare")
                .toString()
                .split(",")).map(Material::valueOf).toList().size());
    }

    @Test
    public void should_load_default_epic() {
        assertEquals(12, Arrays.stream(PropertiesConfiguration.PROPERTIES
                .get("items.epic")
                .toString()
                .split(",")).map(Material::valueOf).toList().size());
    }

}