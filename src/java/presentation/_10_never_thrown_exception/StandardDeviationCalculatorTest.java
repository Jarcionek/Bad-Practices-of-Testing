package presentation._10_never_thrown_exception;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class StandardDeviationCalculatorTest {

    private final StandardDeviationCalculator standardDeviationCalculator = new StandardDeviationCalculator();

    @Test
    public void throwsExceptionIfAnyValueIsNegative() {
        try {
            standardDeviationCalculator.calculate(3.0, 0.0, -2.0);
        } catch (IllegalArgumentException exception) {
            assertThat(exception.getMessage(), is(equalTo("All values must be non-negative. One of the values was -2.0")));
        }
    }

    // other tests for actual implementation

}
