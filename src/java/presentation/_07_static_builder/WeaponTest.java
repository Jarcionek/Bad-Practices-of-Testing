package presentation._07_static_builder;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.CoreMatchers.is;

public class WeaponTest {

    private static Range.Builder builder = Range.builder();
    private static Range originalRange = builder.withMinimum(3).withOptimal(5).withMaximum(9).build();

    private Weapon weapon = new Weapon(originalRange);

    @Test
    public void calculatesRangeWithOneUpgrade() {
        weapon.attachUpgrade(Upgrade.RANGE_ALL_PLUS_THREE);
        Range expectedRange = builder.withMinimum(6).withOptimal(8).withMaximum(12).build();

        Range actualRange = weapon.getRange();

        assertThat(actualRange, is(sameBeanAs(expectedRange)));
    }

    @Test
    public void calculateRangeWithMultipleUpgrades() {
        weapon.attachUpgrade(Upgrade.RANGE_ALL_PLUS_THREE);
        weapon.attachUpgrade(Upgrade.RANGE_MINIMUM_MINUS_ONE);
        weapon.attachUpgrade(Upgrade.RANGE_MAXIMUM_PLUS_FOUR);
        Range expectedRange = builder.withMinimum(5).withOptimal(8).withMaximum(16).build();

        Range actualRange = weapon.getRange();

        assertThat(actualRange, is(sameBeanAs(expectedRange)));
    }

}
