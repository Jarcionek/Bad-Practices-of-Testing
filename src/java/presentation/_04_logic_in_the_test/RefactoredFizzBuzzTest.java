package presentation._04_logic_in_the_test;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static presentation._04_logic_in_the_test.FizzBuzz.fizzBuzz;

public class RefactoredFizzBuzzTest {

    @Test
    public void returnsFizzForNumbersDivisibleByThree() {
        assertThat(fizzBuzz(3), is(equalTo("fizz")));
    }

    @Test
    public void returnsBuzzForNumbersDivisibleByFive() {
        assertThat(fizzBuzz(5), is(equalTo("buzz")));
    }

    @Test
    public void returnsFizzBuzzForNumbersDivisibleByFifteen() {
        assertThat(fizzBuzz(15), is(equalTo("fizzbuzz")));
    }

    @Test
    public void returnsTheNumberForNumbersNotDivisbleByThreeNorFive() {
        assertThat(fizzBuzz(4), is(equalTo("4")));
    }

}
