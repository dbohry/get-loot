package mobs;

import com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe.Zombie;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LootFromZombieTest {

    private Zombie mob;

    @Before
    public void setup() {
        mob = new Zombie();
    }

    @Ignore
    @Test
    public void shouldCreateLootFromMob() {
        List<ItemStack> result = mob.prepareLoot();
        assertTrue(result.size() > 0);
    }

}
