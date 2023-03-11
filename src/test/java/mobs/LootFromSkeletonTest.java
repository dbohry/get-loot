package mobs;

import com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LootFromSkeletonTest {

    private Skeleton mob;

    @Before
    public void setup() {
        mob = new Skeleton();
    }

    @Ignore
    @Test
    public void shouldCreateLootFromMob() {
        List<ItemStack> result = mob.prepareLoot();
        assertTrue(result.size() > 0);
    }

}
