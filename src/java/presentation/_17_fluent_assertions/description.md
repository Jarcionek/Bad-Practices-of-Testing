## AssertJ and fluent assertions


### Domain:

We are trying to assert that some text contains digits only. Pattern `\d+` matches one or more digits.


### Test code:

```java
import static org.assertj.core.api.Assertions.assertThat;

public class VerySmallTest {

    @Test
    public void test() {
        assertThat("text".matches("\\d+"));
    }

}
```


### Problem:

This test passes, but it should not. This problem is related to fluent interface - AssertJ uses method chaining and uses extensive overloading of method `assertThat(...)`. This is really nice feature, as depending on the type of the argument, you get different options, e.g. if you pass a boolean, you will have methods `isTrue()` and `isFalse()` available and if you provide a String, you will have method `matches(regex)` shown in the auto completion. However, `assertThat(...)` does effectively nothing if it is not followed by `.isEqualTo(...)` or any other alternative method and it is exactly what happened in this case. 


### Solution:

In regards to just this test - the code should be changed to either `assertThat("text").matches("\\d+")` or `assertThat("text".matches("\\d+")).isTrue()` (pay attention to the position of closing parentheses).

In regards to the problem overall, the extreme recommendation is to use a different framework which enforces that such mistakes cannot be made, ideally at compile time. However, this problem really comes down to "see the test fail".


#### [Back to main page](https://github.com/Jarcionek/Bad-Practices-of-Testing)
