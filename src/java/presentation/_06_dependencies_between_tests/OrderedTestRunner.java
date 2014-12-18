package presentation._06_dependencies_between_tests;

import org.junit.Test;

public class OrderedTestRunner {

    private int count = 1;

    @Test
    public void runTestsInOrder() {
        runFirstTest();
        runSecondTest();
        runFirstTest();

        // you should see in the console that first test run after running second test has failed
    }

    private void runFirstTest() {
        try {
            new EventProcessorTest().doesNotLogEventIfDebugIsOff();
            System.out.println(count + " First test passed");
        } catch (AssertionError error) {
            System.err.println(count + " First test failed");
        }
        count++;
    }

    private void runSecondTest() {
        try {
            new EventProcessorTest().logsEventIfDebugIsOn();
            System.out.println(count + " Second test passed");
        } catch (AssertionError error) {
            System.err.println(count + " Second test failed");
        }
        count++;
    }

}
