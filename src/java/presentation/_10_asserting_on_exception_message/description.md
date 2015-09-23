## Asserting on exception's message


### Domain:

Standard Deviation is a measure of spread of the numbers from its average value. It can be calculated from any numbers, however in our specific case we expect only non-negative numbers. If any number is negative an exception is expected.


### Test Code:

```java
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
```


### Problem:

This test does not drive us to write any code. Assuming the algorithm is implemented but the arguments checks are not, if you write and run this test it will be green straight away. This is because exception is never thrown and the assertion line is never executed.

Another minor problem may be a very strict assertion on the error message. It may be desired, however in many cases we do not really care about the details such as full stop at the end of sentence or whether the sentence starts with capital character. You may want to consider asserting only that the message contains certain keywords such as `non-negative` and `-2.0`.


### Solution:

```java
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
```

or

```java
public class RefactoredStandardDeviationCalculatorTest {

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
```



#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_11_swallowing_assertion_error/description.md)
