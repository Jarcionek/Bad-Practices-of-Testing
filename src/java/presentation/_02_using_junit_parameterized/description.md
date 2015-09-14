## Using JUnit Parameterized


### Domain:

A new startup wants to open a shop online. There is very little code and for the time being many things are hardcoded in the code (e.g. products' prices). `PriceCalculator` sums the total price of all the products in the cart. The system is using `int`s to represent a price in pennies.


### Test code:

```java
@RunWith(Parameterized.class)
public class PriceCalculatorTest {

    private static final Product APPLE = new Product("Apple");
    private static final Product ORANGE = new Product("Orange");

    private final PriceCalculator priceCalculator = new PriceCalculator();

    private final List<Product> products;
    private final int expectedTotalPrice;

    @Parameters
    public static Collection data() {
        return asList(new Object[][] {
                {asList(APPLE, ORANGE), priceOf(APPLE) + priceOf(ORANGE)},
                {asList(APPLE, ORANGE, APPLE, APPLE), priceOf(APPLE) * 2 + priceOf(ORANGE)},
                {asList(APPLE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE),
                                                                   priceOf(APPLE) + priceOf(ORANGE) * 3},
        });
    }

    public PriceCalculatorTest(List<Product> products, int expectedTotalPrice) {
        this.products = products;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Test
    public void test() {
        int totalPrice = priceCalculator.calculateTotal(products);

        assertTrue(totalPrice == expectedTotalPrice);
    }

}
```


### Problem:

First problem is meaningless tests names. The intention is lost and the expectations look incorrect - why orange and 3 apples have expected price of orange and only 2 apples? There is no way to figure out from the test that `PriceCalculator` has some offers for apples and oranges.

But this is not the only problem. What diagnostics will this test give?

![alt text](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_02_using_junit_parameterized/02-before-intellij.png)

There is absolutely nothing that could explain why the third test failed - you cannot even see what the actual value was. Also you have to count the test parameters to find what input it was. Although it is not a problem with only 3 test parameters, it may get more difficult with 10 or more.

Final problem of junit parameterized test is that it is not possible to debug easily. You cannot just run a chosen test, you have to run them all. So the best you can do is use conditional breakpoints, but this involves a lot of manual work if other test starts failing or if you have multiple breakpoints (as you have to either set all of them to be conditional or disable them temporary).


### Solution:

Tests need meaningful names and there are a few options to fix it:
- convert this test into three test methods (i.e. do not use junit parameterized)
- use testng instead
- in junit 4.11 or later, use a `name` value on the `Parameters` annotation:

```java
public PriceCalculatorTest(String testName, List<Product> products, int expectedTotalPrice) {
    this.products = products;
    this.expectedTotalPrice = expectedTotalPrice;
}

@Parameters(name = "{index}: {0}")
public static Collection data() {
    return asList(new Object[][] {
            {
                    "calculates total price without offers",
                    asList(APPLE, ORANGE),
                    priceOf(APPLE) + priceOf(ORANGE)
            },
            {
                    "calculates total price with apples in offer 'buy two get one free'",
                    asList(APPLE, ORANGE, APPLE, APPLE),
                    priceOf(APPLE) * 2 + priceOf(ORANGE)
            },
            {
                    "calculates total price with oranges in offer 'buy three get two free'",
                    asList(APPLE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE),
                    priceOf(APPLE) + priceOf(ORANGE) * 3
            },
    });
}
```

This will give the following diagnostics:

![alt text](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_02_using_junit_parameterized/02-after-intellij.png)

Further improvement of diagnostics is to use hamcrest and its `assertThat` or at least `assertEquals` to get a `ComparisonFailure` rather than `AssertionError`. I would not use `assertTrue` even for asserting on boolean values, as it does not clearly say what was expected and what was actual. The message will be something like `java.lang.AssertionError: false` and it is not helpful when the test fails so you might consider also adding a meaningful message to your assertion.


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_03_logic_in_the_test/description.md)
