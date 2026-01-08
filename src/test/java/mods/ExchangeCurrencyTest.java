package mods;

import com.lhamacorp.minecraft.plugins.java.getloot.mods.ExchangeCurrency;
import org.bukkit.Material;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ExchangeCurrencyTest {

    private ExchangeCurrency handler;

    @Before
    public void setup() {
        handler = new ExchangeCurrency();
    }

    @Test
    public void shouldCalculateMaxRarityScore() {
        Set<Material> materials = Set.of(Material.EMERALD, Material.LAPIS_LAZULI, Material.GOLD_INGOT);

        int result = handler.calculateRarityScoreFromMaterials(materials);

        assertEquals(1000, result);
    }

    @Test
    public void shouldCalculateHighRarityScore() {
        Set<Material> materials = Set.of(Material.EMERALD, Material.LAPIS_LAZULI);

        int result = handler.calculateRarityScoreFromMaterials(materials);

        assertEquals(999, result);
    }

    @Test
    public void shouldCalculateMediumRarityScore() {
        Set<Material> materials = Set.of(Material.GOLD_INGOT, Material.IRON_INGOT);

        int result = handler.calculateRarityScoreFromMaterials(materials);

        assertEquals(990, result);
    }

    @Test
    public void shouldCalculateLowRarityScore() {
        Set<Material> materials = Set.of(Material.GOLD_NUGGET, Material.IRON_NUGGET);

        int result = handler.calculateRarityScoreFromMaterials(materials);

        assertEquals(950, result);
    }

    @Test
    public void shouldReturnZeroForInvalidCombinations() {
        Set<Material>[] invalidCombinations = new Set[]{
                Set.of(Material.DIAMOND),
                Set.of(Material.EMERALD, Material.DIAMOND),
                Set.of(Material.EMERALD, Material.LAPIS_LAZULI, Material.DIAMOND),
                Set.of(Material.EMERALD, Material.LAPIS_LAZULI, Material.GOLD_INGOT, Material.DIAMOND)
        };

        for (Set<Material> materials : invalidCombinations) {
            int result = handler.calculateRarityScoreFromMaterials(materials);
            assertEquals(0, result);
        }
    }

    @Test
    public void shouldHandleEmptyMaterialSet() {
        Set<Material> emptySet = Set.of();

        int result = handler.calculateRarityScoreFromMaterials(emptySet);

        assertEquals(0, result);
    }

    @Test
    public void shouldHandleSetOrderIndependence() {
        Set<Material> materials1 = Set.of(Material.EMERALD, Material.LAPIS_LAZULI);
        Set<Material> materials2 = Set.of(Material.LAPIS_LAZULI, Material.EMERALD);

        int result1 = handler.calculateRarityScoreFromMaterials(materials1);
        int result2 = handler.calculateRarityScoreFromMaterials(materials2);

        assertEquals(result1, result2);
        assertEquals(999, result1);
    }

    @Test
    public void shouldTestAllValidCombinations() {
        Set<Material> combo1000 = Set.of(Material.EMERALD, Material.LAPIS_LAZULI, Material.GOLD_INGOT);
        Set<Material> combo999 = Set.of(Material.EMERALD, Material.LAPIS_LAZULI);
        Set<Material> combo990 = Set.of(Material.GOLD_INGOT, Material.IRON_INGOT);
        Set<Material> combo950 = Set.of(Material.GOLD_NUGGET, Material.IRON_NUGGET);

        assertEquals(1000, handler.calculateRarityScoreFromMaterials(combo1000));
        assertEquals(999, handler.calculateRarityScoreFromMaterials(combo999));
        assertEquals(990, handler.calculateRarityScoreFromMaterials(combo990));
        assertEquals(950, handler.calculateRarityScoreFromMaterials(combo950));
    }
}