package utils;

import com.lhamacorp.minecraft.plugins.java.getloot.utils.LootHelper;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.COMMON;
import static org.bukkit.Material.*;
import static org.junit.Assert.assertFalse;

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

}
