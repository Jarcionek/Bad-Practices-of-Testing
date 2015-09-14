package presentation._07_non_thread_safe_tests;

import org.junit.Test;

public class ParallelTestRunner {

    @Test
    public void runBothTestsRepeatedlyInParallel() throws InterruptedException {
        Thread one = new Thread("one") {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    Thread.yield();
                    new WeaponTest().calculatesRangeWithOneUpgrade();
                }
            }
        };
        Thread two = new Thread("two") {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    Thread.yield();
                    new WeaponTest().calculatesRangeWithMultipleUpgrades();
                }
            }
        };

        one.start();
        two.start();

        one.join();
        two.join();

        // you should see in the console that one of the threads has thrown org.junit.ComparisonFailure, for example:
        //Exception in thread "two" org.junit.ComparisonFailure: maximum
        //Expected: 12
        //got: 16
        //; minimum
        //Expected: 6
        //got: 5
        //expected:<{
        //    "minimum": [6,
        //    "optimal": 8,
        //    "maximum": 12]
        //}> but was:<{
        //    "minimum": [5,
        //    "optimal": 8,
        //    "maximum": 16]
        //}>
        //at com.shazam.shazamcrest.ResultComparison.containsComparableJson(ResultComparison.java:33)
        //at com.shazam.shazamcrest.MatcherAssert.assertThat(MatcherAssert.java:48)
        //at com.shazam.shazamcrest.MatcherAssert.assertThat(MatcherAssert.java:29)
        //at presentation._07_non_thread_safe_tests.WeaponTest.calculatesRangeWithMultipleUpgrades(WeaponTest.java:39)
        //at presentation._07_non_thread_safe_tests.ParallelTestRunner$2.run(ParallelTestRunner.java:23)
    }

}
