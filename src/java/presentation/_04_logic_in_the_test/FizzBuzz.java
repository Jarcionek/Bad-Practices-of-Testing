package presentation._04_logic_in_the_test;

public class FizzBuzz {

    public static String fizzBuzz(int n) {
        if (n % 3 == 0) {
           return "fizz";
        } else if (n % 5 == 0) {
            return "buzz";
        } else if (n % 15 == 0) {
            return "fizzbuzz";
        } else {
            return "" + n;
        }
    }

}
