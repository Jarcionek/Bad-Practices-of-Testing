package presentation._07_non_thread_safe_tests;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.CoreMatchers.is;

public class WeaponTest {

    private static final int MINIMUM = 3;
    private static final int OPTIMAL = 5;
    private static final int MAXIMUM = 9;

    private static Range.Builder rangeBuilder = Range.builder();
    private static Range originalRange = rangeBuilder.withMinimum(MINIMUM).withOptimal(OPTIMAL).withMaximum(MAXIMUM).build();

    private final Weapon weapon = new Weapon(originalRange);

    @Test
    public void calculatesRangeWithOneUpgrade() {
        Range expectedRange = rangeBuilder.withMinimum(MINIMUM + 3).withOptimal(OPTIMAL + 3).withMaximum(MAXIMUM + 3).build();

        weapon.attachUpgrade(Upgrade.RANGE_ALL_PLUS_THREE);
        Range actualRange = weapon.getRange();

        assertThat(actualRange, is(sameBeanAs(expectedRange)));
    }

    @Test
    public void calculatesRangeWithMultipleUpgrades() {
        Range expectedRange = rangeBuilder.withMinimum(MINIMUM + 3 - 1).withOptimal(OPTIMAL + 3).withMaximum(MAXIMUM + 3 + 4).build();

        weapon.attachUpgrade(Upgrade.RANGE_ALL_PLUS_THREE);
        weapon.attachUpgrade(Upgrade.RANGE_MINIMUM_MINUS_ONE);
        weapon.attachUpgrade(Upgrade.RANGE_MAXIMUM_PLUS_FOUR);
        Range actualRange = weapon.getRange();

        assertThat(actualRange, is(sameBeanAs(expectedRange)));
    }

}
