## Using JUnit Parameterized


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
                {asList(APPLE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE), priceOf(APPLE) + priceOf(ORANGE) * 3},
        });
    }

    public PriceCalculatorTest(List<Product> products, int expectedTotalPrice) {
        this.products = products;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Test
    public void test() {
        int totalPrice = priceCalculator.calculateTotal(products);

        assertThat(totalPrice, is(equalTo(expectedTotalPrice)));
    }

}
```


### Problem:

Meaningless tests names. The intention is lost and the expectations look incorrect - why orange and 3 apples have expected price of orange and 2 apples? There is no way to figure out from the test that ```PriceCalculator``` has some offers for apples and oranges.

But this is not the only problem. What diagnostics will this test give?

//TODO Jarek: paste screenshots

//TODO Jarek: describe bad diagnostics

//TODO Jarek: describe inability to debug easily

### Solution:

//TODO Jarek: describe solution
