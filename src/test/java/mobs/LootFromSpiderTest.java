package mobs;

import com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe.Spider;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class LootFromSpiderTest {

    private Spider mob;

    @Before
    public void setup() {
        mob = new Spider();
    }

    @Ignore
    @Test
    public void shouldCreateLootFromMob() {
        List<ItemStack> result = mob.prepareLoot();
        assertFalse(result.isEmpty());
    }

}
