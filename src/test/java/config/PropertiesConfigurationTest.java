package config;

import com.lhamacorp.minecraft.plugins.java.getloot.config.PropertiesConfiguration;
import org.bukkit.Material;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

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

    @Test
    public void should_load_valid_material_values() {
        // Test that all loaded materials are valid Minecraft materials, not just counts

        // Test common currency materials
        String commonCurrencyString = PropertiesConfiguration.PROPERTIES.get("items.common-currency").toString();
        String[] commonCurrencyMaterials = commonCurrencyString.split(",");

        for (String materialName : commonCurrencyMaterials) {
            try {
                Material material = Material.valueOf(materialName.trim());
                assertNotNull("Material should not be null: " + materialName, material);
                assertNotEquals("Material should not be AIR: " + materialName, Material.AIR, material);
            } catch (IllegalArgumentException e) {
                fail("Invalid material name in common-currency: " + materialName);
            }
        }

        // Test that epic materials contain high-value items
        String epicString = PropertiesConfiguration.PROPERTIES.get("items.epic").toString();
        String[] epicMaterials = epicString.split(",");

        boolean foundHighValueItem = false;
        for (String materialName : epicMaterials) {
            Material material = Material.valueOf(materialName.trim());
            // Check for typical high-value epic items
            if (material == Material.NETHERITE_SWORD || material == Material.NETHERITE_AXE ||
                material == Material.ELYTRA || material == Material.TOTEM_OF_UNDYING ||
                material == Material.BEACON || material == Material.DRAGON_EGG) {
                foundHighValueItem = true;
                break;
            }
        }
        assertTrue("Epic items should contain at least one high-value material", foundHighValueItem);
    }

    @Test
    public void should_handle_properties_loading_correctly() {
        // Test that the PROPERTIES object is properly initialized and contains expected keys

        assertNotNull("PROPERTIES should not be null", PropertiesConfiguration.PROPERTIES);
        assertTrue("PROPERTIES should not be empty", PropertiesConfiguration.PROPERTIES.size() > 0);

        // Test that all expected property keys exist
        String[] expectedKeys = {
            "items.common-currency",
            "items.rare-currency",
            "items.very-common",
            "items.common",
            "items.rare",
            "items.very-rare",
            "items.epic"
        };

        for (String key : expectedKeys) {
            assertTrue("Property key should exist: " + key,
                      PropertiesConfiguration.PROPERTIES.containsKey(key));
            assertNotNull("Property value should not be null: " + key,
                         PropertiesConfiguration.PROPERTIES.get(key));

            String value = PropertiesConfiguration.PROPERTIES.get(key).toString();
            assertFalse("Property value should not be empty: " + key, value.trim().isEmpty());
            assertTrue("Property value should contain materials: " + key, value.contains(",") || !value.trim().isEmpty());
        }

        // Test that properties can be parsed without throwing exceptions
        for (String key : expectedKeys) {
            String value = PropertiesConfiguration.PROPERTIES.get(key).toString();
            String[] materials = value.split(",");

            assertTrue("Property should have at least one material: " + key, materials.length > 0);

            for (String materialName : materials) {
                try {
                    Material.valueOf(materialName.trim());
                } catch (IllegalArgumentException e) {
                    fail("Invalid material in " + key + ": " + materialName);
                }
            }
        }
    }

}