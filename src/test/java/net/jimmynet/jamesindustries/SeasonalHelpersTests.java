package net.jimmynet.jamesindustries;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import net.jimmynet.jamesindustries.helpers.SeasonalHelpers;

public class SeasonalHelpersTests {
    private SeasonalHelpersTests() {}

    @Test
    void easterSundayShouldBeAroundEaster() {
        SeasonalHelpers seasons = new SeasonalHelpers(
            LocalDate.of(2026, 4, 5)
        );

        Assertions.assertTrue(seasons.isAroundEaster());
    }
}