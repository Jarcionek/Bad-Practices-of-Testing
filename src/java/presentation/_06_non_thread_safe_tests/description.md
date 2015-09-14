## Non thread-safe unit tests


### Domain:

In a browser game there are weapons whose damage and accuracy are different depending on the range. Each weapon is described by three parameters - minimum range, optimal range, maximum range. It is possible to attach to the weapon one or multiple upgrades that modify these three parameters. This test is to make sure that actual range of the weapon includes all upgrades attached to it.


### Test code:

```java
public class WeaponTest {

    private static final int MINIMUM = 3;
    private static final int OPTIMAL = 5;
    private static final int MAXIMUM = 9;

    private static Range.Builder rangeBuilder = Range.builder();
    private static Range originalRange = rangeBuilder.withMinimum(MINIMUM).withOptimal(OPTIMAL)
                                                     .withMaximum(MAXIMUM).build();

    private Weapon weapon = new Weapon(originalRange);

    @Test
    public void calculatesRangeWithOneUpgrade() {
        weapon.attachUpgrade(Upgrade.RANGE_ALL_PLUS_THREE);
        Range expectedRange = rangeBuilder.withMinimum(MINIMUM + 3).withOptimal(OPTIMAL + 3)
                                          .withMaximum(MAXIMUM + 3).build();

        Range actualRange = weapon.getRange();

        assertThat(actualRange, is(sameBeanAs(expectedRange)));
    }

    @Test
    public void calculatesRangeWithMultipleUpgrades() {
        weapon.attachUpgrade(Upgrade.RANGE_ALL_PLUS_THREE);
        weapon.attachUpgrade(Upgrade.RANGE_MINIMUM_MINUS_ONE);
        weapon.attachUpgrade(Upgrade.RANGE_MAXIMUM_PLUS_FOUR);
        Range expectedRange = rangeBuilder.withMinimum(MINIMUM + 3 - 1).withOptimal(OPTIMAL + 3)
                                          .withMaximum(MAXIMUM + 3 + 4).build();

        Range actualRange = weapon.getRange();

        assertThat(actualRange, is(sameBeanAs(expectedRange)));
    }

}
```


### Problem:

The implementation is correct, the tests are correct as well and they are passing. But depending on the setup in which the tests are running, they may be randomly failing. There is a minor setup flaw which will cause problems if tests are run in parallel. The builder declared is a mutable object - it is static and shared between tests. If both tests call `withMinimum` at the same time, the second test will override whatever the first test has done, causing the first test to fail. If your Continuous Integration System is running tests in parallel but local set up does not, the issue will be impossible to reproduce locally and may be very difficult to identify.


### Solution:

Remove `static` keyword from the builder declaration. Do not declare in tests any static mutable objects.


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_08_asserting_on_the_elements_of_the_list/description.md)
