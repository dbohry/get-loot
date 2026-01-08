package utils;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.*;
import static org.bukkit.Material.*;
import static org.junit.Assert.*;

public class LootHelperTest {

    private LootHelper helper;

    @Before
    public void setup() {
        helper = new LootHelper();
    }

    @Test
    public void shouldCreateLoot() {
        List<ItemStack> result = helper.createLoot(COMMON, 1, 10f);
        assertFalse(result.isEmpty());
    }

    @Test
    public void shouldCreateLootWithCustom() {
        List<Material> customItems = Arrays.asList(
                WOODEN_SWORD,
                STONE_SWORD,
                IRON_SWORD,
                DIAMOND_SWORD
        );

        List<ItemStack> result = helper.createLoot(COMMON, 1, 10, customItems);

        assertFalse(result.isEmpty());
    }

    @Test
    public void shouldCreateLootForAllRarityTypes() {
        // Test that all rarity types can generate loot
        Rarity[] raritiesToTest = {VERY_COMMON, COMMON, RARE, VERY_RARE, EPIC, COMMON_CURRENCY, RARE_CURRENCY};

        for (Rarity rarity : raritiesToTest) {
            List<ItemStack> result = helper.createLoot(rarity, 5, 10.0f);
            assertNotNull("Loot should not be null for rarity: " + rarity, result);
            // Note: Result can be empty due to probability, but should never be null
        }
    }

    @Test
    public void shouldCreateSingleItemLoot() {
        ItemStack result = helper.createLoot(COMMON);

        assertNotNull("Single item loot should not be null", result);
        assertEquals("Single item should have quantity 1", 1, result.getAmount());
        assertNotNull("ItemStack should have a valid material", result.getType());
    }

    @Test
    public void shouldHandleZeroMultiplier() {
        List<ItemStack> result = helper.createLoot(VERY_COMMON, 10, 0.0f);

        assertNotNull("Result should not be null with zero multiplier", result);
        // With zero multiplier, probability will be 0, so loot should likely be empty
        // But we're testing that no exceptions are thrown
    }

    @Test
    public void shouldHandleNegativeMultiplier() {
        List<ItemStack> result = helper.createLoot(VERY_COMMON, 5, -1.0f);

        assertNotNull("Result should not be null with negative multiplier", result);
        // Negative multiplier should result in negative probability, no loot should be generated
    }

    @Test
    public void shouldHandleHighMultiplier() {
        List<ItemStack> result = helper.createLoot(EPIC, 3, 1000.0f);

        assertNotNull("Result should not be null with high multiplier", result);
        // With very high multiplier, probability of getting loot should be very high
        // but due to stack.isEmpty() guard in buildItemStack, max 1 item per call
    }

    @Test
    public void shouldCreateValidItemStacks() {
        List<ItemStack> result = helper.createLoot(COMMON, 10, 5.0f);

        assertNotNull("Result should not be null", result);

        for (ItemStack item : result) {
            assertNotNull("ItemStack should not be null", item);
            assertNotNull("ItemStack should have valid material", item.getType());
            assertTrue("ItemStack quantity should be positive", item.getAmount() > 0);
            assertNotEquals("Material should not be AIR", Material.AIR, item.getType());
        }
    }

    @Test
    public void shouldIntegrateCustomLootCorrectly() {
        List<Material> customItems = Arrays.asList(NETHERITE_SWORD, ELYTRA, TOTEM_OF_UNDYING);

        List<ItemStack> result = helper.createLoot(RARE, 2, 10.0f, customItems);

        assertNotNull("Result with custom loot should not be null", result);

        // The method should create both regular RARE loot and CUSTOM loot
        for (ItemStack item : result) {
            assertNotNull("ItemStack should not be null", item);
            assertNotNull("ItemStack should have valid material", item.getType());
            assertTrue("ItemStack quantity should be positive", item.getAmount() > 0);
        }
    }

    @Test
    public void shouldHandleMultipleTriesCorrectly() {
        // Test with different try counts to ensure the loop logic works
        List<ItemStack> result1 = helper.createLoot(VERY_COMMON, 1, 2.0f);
        List<ItemStack> result5 = helper.createLoot(VERY_COMMON, 5, 2.0f);
        List<ItemStack> result10 = helper.createLoot(VERY_COMMON, 10, 2.0f);

        assertNotNull("Result with 1 try should not be null", result1);
        assertNotNull("Result with 5 tries should not be null", result5);
        assertNotNull("Result with 10 tries should not be null", result10);

        // Due to stack.isEmpty() guard, each call should return at most 1 item
        // regardless of try count, but we're testing that different try counts don't break
        assertTrue("Result with 1 try should have <= 1 items", result1.size() <= 1);
        assertTrue("Result with 5 tries should have <= 1 items", result5.size() <= 1);
        assertTrue("Result with 10 tries should have <= 1 items", result10.size() <= 1);
    }

}
