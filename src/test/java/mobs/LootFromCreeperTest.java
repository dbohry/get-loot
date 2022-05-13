package mobs;

import com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe.Creeper;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LootFromCreeperTest {

  private Creeper mob;

  @Before
  public void setup() {
    mob = new Creeper();
  }

  @Test
  public void shouldCreateLootFromMob() {
    List<ItemStack> result = mob.prepareLoot();
    assertTrue(result.size() > 0);
  }

}
