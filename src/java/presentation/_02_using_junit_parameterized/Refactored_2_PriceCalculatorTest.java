package presentation._02_using_junit_parameterized;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static presentation._02_using_junit_parameterized.Product.priceOf;

@RunWith(Parameterized.class)
public class Refactored_2_PriceCalculatorTest {

    private static final Product APPLE = new Product("Apple");
    private static final Product ORANGE = new Product("Orange");

    private final PriceCalculator priceCalculator = new PriceCalculator();

    private final List<Product> products;
    private final int expectedTotalPrice;

    public Refactored_2_PriceCalculatorTest(String testName, List<Product> products, int expectedTotalPrice) {
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

    @Test
    public void test() {
        int totalPrice = priceCalculator.calculateTotal(products);

        assertThat(totalPrice, is(equalTo(expectedTotalPrice)));
    }

}
