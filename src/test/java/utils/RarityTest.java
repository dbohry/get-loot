package utils;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import org.junit.Test;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.*;
import static org.junit.Assert.*;

public class RarityTest {

    @Test
    public void shouldCalculateRarityFromScore() {
        int score = 1;
        Rarity result = rarityFromScore(score);
        assertEquals(500, result.getRarity());
        assertEquals(COMMON_CURRENCY.name(), result.name());

        score = 890;
        result = rarityFromScore(score);
        assertEquals(900, result.getRarity());
        assertEquals(VERY_COMMON.name(), result.name());

        score = 985;
        result = rarityFromScore(score);
        assertEquals(990, result.getRarity());
        assertEquals(RARE.name(), result.name());

        score = 1001;
        result = rarityFromScore(score);
        assertEquals(1000, result.getRarity());
        assertEquals(EPIC.name(), result.name());

    }

    @Test
    public void shouldCalculateRarityForAllEnumValues() {
        // Test all 8 rarity enum values with exact score matches

        // COMMON_CURRENCY (500)
        Rarity result = rarityFromScore(500);
        assertEquals("Score 500 should map to COMMON_CURRENCY", COMMON_CURRENCY, result);
        assertEquals(500, result.getRarity());

        // VERY_COMMON (900)
        result = rarityFromScore(900);
        assertTrue("Score 900 should map to either VERY_COMMON or CUSTOM",
                result == VERY_COMMON || result == CUSTOM);
        assertEquals(900, result.getRarity());

        // COMMON (950)
        result = rarityFromScore(950);
        assertEquals("Score 950 should map to COMMON", COMMON, result);
        assertEquals(950, result.getRarity());

        // RARE (990) or RARE_CURRENCY (990) - both have same rarity value
        result = rarityFromScore(990);
        assertTrue("Score 990 should map to either RARE or RARE_CURRENCY",
                result == RARE || result == RARE_CURRENCY);
        assertEquals(990, result.getRarity());

        // VERY_RARE (999)
        result = rarityFromScore(999);
        assertEquals("Score 999 should map to VERY_RARE", VERY_RARE, result);
        assertEquals(999, result.getRarity());

        // EPIC (1000)
        result = rarityFromScore(1000);
        assertEquals("Score 1000 should map to EPIC", EPIC, result);
        assertEquals(1000, result.getRarity());
    }

    @Test
    public void shouldHandleTieBreakingScenarios() {
        // Test tie-breaking for rarity values that have the same getRarity() value

        // Test VERY_COMMON vs CUSTOM (both 900)
        // The algorithm picks the first one found in the enum iteration
        Rarity result900 = rarityFromScore(900);
        assertEquals("Tie at 900 should be resolved consistently", 900, result900.getRarity());
        assertTrue("Should be either VERY_COMMON or CUSTOM",
                result900 == VERY_COMMON || result900 == CUSTOM);

        // Test RARE vs RARE_CURRENCY (both 990)
        Rarity result990 = rarityFromScore(990);
        assertEquals("Tie at 990 should be resolved consistently", 990, result990.getRarity());
        assertTrue("Should be either RARE or RARE_CURRENCY",
                result990 == RARE || result990 == RARE_CURRENCY);

        // Test equidistant scores
        // Score 725 is equidistant from COMMON_CURRENCY (500) and VERY_COMMON (900)
        // Should pick the one with minimum distance (both have distance 225)
        Rarity resultEquidistant = rarityFromScore(725);
        assertTrue("Equidistant score should pick one of the closest rarities",
                resultEquidistant == COMMON_CURRENCY || resultEquidistant == VERY_COMMON || resultEquidistant == CUSTOM);
    }

    @Test
    public void shouldHandleExtremeScores() {
        // Test extreme and edge case scores

        // Score 0 (far from all rarity values)
        Rarity resultZero = rarityFromScore(0);
        assertNotNull("Should return a rarity for score 0", resultZero);
        assertEquals("Score 0 should map to COMMON_CURRENCY (closest)", COMMON_CURRENCY, resultZero);

        // Very high score (far from all rarity values)
        Rarity resultHigh = rarityFromScore(2000);
        assertNotNull("Should return a rarity for score 2000", resultHigh);
        assertEquals("Score 2000 should map to EPIC (closest)", EPIC, resultHigh);

        // Negative score
        Rarity resultNegative = rarityFromScore(-100);
        assertNotNull("Should return a rarity for negative score", resultNegative);
        assertEquals("Negative score should map to COMMON_CURRENCY (closest)", COMMON_CURRENCY, resultNegative);

        // Score exactly between two values
        // Score 975 is exactly between COMMON (950) and EPIC (1000) - distance 25 each
        Rarity resultBetween = rarityFromScore(975);
        assertNotNull("Should return a rarity for score between two values", resultBetween);
        assertTrue("Score 975 should be closest to COMMON, RARE, RARE_CURRENCY, or VERY_RARE",
                resultBetween == COMMON || resultBetween == RARE ||
                resultBetween == RARE_CURRENCY || resultBetween == VERY_RARE);
    }

}
