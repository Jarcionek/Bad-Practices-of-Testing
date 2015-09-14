## Logic in the test


### Domain:

FizzBuzz is the most basic and at the same time the most challenging exercise given to developers. The function is supposed to take a number and return "fizz" if number is divisible by 3, "buzz" if number is divisible by 5, "fizzbuzz" if number is divisible by both 3 and 5 and finally if parameter is not divisible by 3 or 5, the function is supposed to return a number itself. Here is a unit test testing this function named `fizzBuzz`.


### Test Code:

```java
@Test
public void testFizzBuzz() {
    for (int i = 1; i < 100; i++) {
        String result = fizzBuzz(i);
        if (i % 3 == 0) {
            assertEquals("fizz", result);
        } else if (i % 5 == 0) {
            assertEquals("buzz", result);
        } else if (i % 15 == 0) {
            assertEquals("fizzbuzz", result);
        } else {
            assertEquals("" + i, result);
        }
    }
}
```


### Problem:

Aside meaningless tests name, multiple failure reasons, poor diagnostics and inability to debug (without using conditional breakpoints) this test contains logic. If we wrote such test and now have to implement it, would we be writing almost the same code again? Of course not, we are lazy - we will just copy this code, remove the loop, replace assertions with return statements, done! However, this logic contains a bug and so does our implementation, but we have a false confidence that everything is fine because we have tests.


### Solution:

Delete this test and replace is with these four tests:

```java
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
```

### Notes:

You could say that the simplest implementation to make it pass is to just use comparison on the number instead of having division and checking on the remainder. Although this is true, the goal of tests is to help us implement the feature, not to show that they are passing despite feature being incomplete/incorrect. We know what we aim for and our code will be either reviewed or written while pair programming, so it’s unlikely that such problem would arise in real life. However, if it doesn’t persuade you, feel free to add 3 extra tests for 6, 10 and 30.


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_05_expectation_set_up_in_production_code/description.md)
