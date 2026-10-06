package net.jimmynet.jamesindustries;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;

import net.jimmynet.jamesindustries.helpers.SeasonalHelpers;

public final class SeasonalHelpersTests {
    private SeasonalHelpersTests() {}

    @Test
    void easterSundayShouldBeAroundEaster() {
        SeasonalHelpers seasons = new SeasonalHelpers(
            LocalDate.of(2026, 4, 5)
        );

        Assertions.assertTrue(seasons.isAroundEaster());
    }

    @Test
    void dayBeforeEasterWindow() {
        SeasonalHelpers seasons = new SeasonalHelpers(
            LocalDate.of(2027, 2, 28)
        );

        Assertions.assertFalse(seasons.isAroundEaster());
    }

    @Test 
    void dayAfterEasterWindow() {
        SeasonalHelpers seasons = new SeasonalHelpers(
            LocalDate.of(2027, 5, 1)
        );

        Assertions.assertFalse(seasons.isAroundEaster());
    }

    @Test
    void firstDayOfEasterWindow() {
        SeasonalHelpers seasons = new SeasonalHelpers(
            LocalDate.of(2027, 3, 1)
        );

        Assertions.assertTrue(seasons.isAroundEaster());
    }

    @Test 
    void lastDayOfEasterWindow() {
        SeasonalHelpers seasons = new SeasonalHelpers(
            LocalDate.of(2027, 4, 30)
        );

        Assertions.assertTrue(seasons.isAroundEaster());
    }

    @ParameterizedTest
    @CsvSource({
        "2027-02-28, false",
        "2026-04-05, true",
        "2027-05-01, false",
        "2027-03-01, true",
        "2027-04-30, true",
        "2024-03-31, true",
        "2025-04-18, true",
        "2040-03-30, true"
    })
    void shouldClassifyEasterDates(
        LocalDate date,
        boolean expectedAroundEaster
    ) {
        SeasonalHelpers seasons = new SeasonalHelpers(date);
        Assertions.assertEquals(expectedAroundEaster, seasons.isAroundEaster());
    }
}