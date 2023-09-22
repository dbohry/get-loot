package utils;

import com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity;
import org.junit.Test;

import static com.lhamacorp.minecraft.plugins.java.getloot.enums.Rarity.*;
import static org.junit.Assert.assertEquals;

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

}
