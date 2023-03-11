package mobs;

import com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe.Enderman;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LootFromEndermanTest {

    private Enderman mob;

    @Before
    public void setup() {
        mob = new Enderman();
    }

    @Test
    public void shouldCreateLootFromMob() {
        List<ItemStack> result = mob.prepareLoot();
        assertTrue(result.size() > 0);
    }

}
