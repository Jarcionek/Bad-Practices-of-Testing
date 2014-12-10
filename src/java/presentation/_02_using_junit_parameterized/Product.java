package presentation._02_using_junit_parameterized;

import java.math.BigDecimal;

public class Product {

    private final String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static int priceOf(Product product) {
        return priceOf(product.getName());
    }

    public static int priceOf(String productName) {
        switch (productName) {
            case "Apple":  return 20;
            case "Orange": return 25;
        }
        throw new IllegalArgumentException("No such product: " + productName);
    }

}
