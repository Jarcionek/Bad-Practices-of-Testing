package presentation._07_non_thread_safe_tests;

import java.util.ArrayList;
import java.util.List;

public class Weapon {

    private final Range originalRange;
    private final List<Upgrade> upgrades = new ArrayList<>();

    public Weapon(Range originalRange) {
        this.originalRange = originalRange;
    }

    public void attachUpgrade(Upgrade upgrade) {
        upgrades.add(upgrade);
    }

    public Range getRange() {
        int minimum = originalRange.getMinimum();
        int optimal = originalRange.getOptimal();
        int maximum = originalRange.getMaximum();
        for (Upgrade upgrade : upgrades) {
            minimum += upgrade.getMinimumRangeModifier();
            optimal += upgrade.getOptimalRangeModifier();
            maximum += upgrade.getMaximumRangeModifier();
        }
        return Range.builder().withMinimum(minimum).withOptimal(optimal).withMaximum(maximum).build();
    }

}
