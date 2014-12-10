package presentation._02_using_junit_parameterized;

import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static presentation._02_using_junit_parameterized.Product.priceOf;

public class Refactored_1_PriceCalculatorTest {

    private static final Product APPLE = new Product("Apple");
    private static final Product ORANGE = new Product("Orange");

    private final PriceCalculator priceCalculator = new PriceCalculator();

    @Test
    public void calculatesTotalPriceWithoutOffers() {
        int totalPrice = priceCalculator.calculateTotal(newArrayList(APPLE, ORANGE));

        assertThat(totalPrice, is(equalTo(priceOf(APPLE) + priceOf(ORANGE))));
    }

    @Test
    public void calculatesTotalPriceWithApplesInOfferBuyTwoGetOneFree() {
        int totalPrice = priceCalculator.calculateTotal(newArrayList(APPLE, ORANGE, APPLE, APPLE));

        assertThat(totalPrice, is(equalTo(priceOf(APPLE) * 2 + priceOf(ORANGE))));
    }

    @Test
    public void calculatesTotalPriceWithOrangesInOfferBuyThreeGetTwoFree() {
        int totalPrice = priceCalculator.calculateTotal(newArrayList(APPLE, ORANGE, ORANGE, ORANGE, ORANGE, ORANGE));

        assertThat(totalPrice, is(equalTo(priceOf(APPLE) + priceOf(ORANGE) * 3)));
    }

}