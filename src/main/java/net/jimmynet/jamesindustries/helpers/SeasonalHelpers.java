package net.jimmynet.jamesindustries.helpers;

import java.time.LocalDate;
import java.time.Month;

public final class SeasonalHelpers {

    private LocalDate date;

    public SeasonalHelpers() {
        this(LocalDate.now());
    }

    public SeasonalHelpers(LocalDate date) {
        this.date = date;
    }

    public boolean isAroundEaster() {
        return (date.getMonth() == Month.MARCH || date.getMonth() == Month.APRIL);
    }
}
