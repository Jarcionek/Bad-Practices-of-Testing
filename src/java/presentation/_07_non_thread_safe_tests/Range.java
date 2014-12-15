package presentation._07_non_thread_safe_tests;

public class Range {

    private final int minimum;
    private final int optimal;
    private final int maximum;

    private Range(int minimum, int optimal, int maximum) {
        this.minimum = minimum;
        this.optimal = optimal;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getOptimal() {
        return optimal;
    }

    public int getMaximum() {
        return maximum;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private int minimum;
        private int optimal;
        private int maximum;

        private Builder() {}

        public Builder withMinimum(int minimum) {
            this.minimum = minimum;
            return this;
        }

        public Builder withOptimal(int optimal) {
            this.optimal = optimal;
            return this;
        }

        public Builder withMaximum(int maximum) {
            this.maximum = maximum;
            return this;
        }

        public Range build() {
            return new Range(minimum, optimal, maximum);
        }

    }

}
