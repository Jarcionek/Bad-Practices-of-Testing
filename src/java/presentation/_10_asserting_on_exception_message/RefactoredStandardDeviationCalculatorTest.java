package presentation._10_asserting_on_exception_message;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.fail;

public class RefactoredStandardDeviationCalculatorTest {

    private final StandardDeviationCalculator standardDeviationCalculator = new StandardDeviationCalculator();

    @Test
    public void throwsExceptionIfAnyValueIsNegative() {
        try {
            standardDeviationCalculator.calculate(3.0, 0.0, -2.0);
        } catch (IllegalArgumentException exception) {
            assertThat(exception.getMessage(), allOf(containsString("non-negative"), containsString("-2.0")));
            return;
        }
        fail("Exception expected but not thrown");
    }

    // other tests for actual implementation

}
