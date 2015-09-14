package presentation._04_expectation_set_up_in_production_code;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RefactoredActionValidatorTest {

    private final ActionValidator actionValidator = new ActionValidator();

    @Test
    public void acceptsMove() {
        assertThat(actionValidator.isValidAction("MOVE"), is(equalTo(true)));
    }

    @Test
    public void acceptsTurn() {
        assertThat(actionValidator.isValidAction("TURN"), is(equalTo(true)));
    }

    @Test
    public void acceptsAttack() {
        assertThat(actionValidator.isValidAction("ATTACK"), is(equalTo(true)));
    }

    @Test
    public void doesNotAcceptInvalidAction() {
        assertThat(actionValidator.isValidAction("CROUCH"), is(equalTo(false)));
    }

}