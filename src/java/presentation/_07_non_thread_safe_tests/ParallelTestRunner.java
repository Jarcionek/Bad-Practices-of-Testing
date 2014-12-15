package presentation._07_non_thread_safe_tests;

import org.junit.Test;

public class ParallelTestRunner {

    @Test
    public void runBothTestsRepeatedlyInParallel() throws InterruptedException {
        Thread one = new Thread("one") {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new WeaponTest().calculatesRangeWithOneUpgrade();
                }
            }
        };
        Thread two = new Thread("two") {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new WeaponTest().calculatesRangeWithMultipleUpgrades();
                }
            }
        };

        one.start();
        two.start();

        one.join();
        two.join();

        // you should see in the console that one of the threads has thrown org.junit.ComparisonFailure
    }

}
