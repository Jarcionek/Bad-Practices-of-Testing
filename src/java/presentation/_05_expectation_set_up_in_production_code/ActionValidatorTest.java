package presentation._05_expectation_set_up_in_production_code;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ActionValidatorTest {

    private final ActionValidator actionValidator = new ActionValidator();

    @Test
    public void testValidator() {
        for (Action action : Action.values()) {
            assertTrue(action.toString(), actionValidator.isValidAction(action.toString()));
        }
    }

}