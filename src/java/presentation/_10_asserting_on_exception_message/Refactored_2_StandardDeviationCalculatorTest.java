package presentation._10_asserting_on_exception_message;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;

public class Refactored_2_StandardDeviationCalculatorTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private final StandardDeviationCalculator standardDeviationCalculator = new StandardDeviationCalculator();

    @Test
    public void throwsExceptionIfAnyValueIsNegative() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(allOf(containsString("non-negative"), containsString("-2.0")));

        standardDeviationCalculator.calculate(3.0, 0.0, -2.0);
    }

    // other tests for actual implementation

}
