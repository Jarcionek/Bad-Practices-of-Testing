package presentation._07_non_thread_safe_tests;

public enum Upgrade {

    RANGE_ALL_PLUS_THREE(+3, +3, +3),
    RANGE_MAXIMUM_PLUS_FOUR(0, 0, +4),
    RANGE_MINIMUM_MINUS_ONE(-1, 0, 0);

    private final int minimumRangeModifier;
    private final int optimalRangeModifier;
    private final int maximumRangeModifier;

    Upgrade(int minimumRangeModifier, int optimalRangeModifier, int maximumRangeModifier) {

        this.minimumRangeModifier = minimumRangeModifier;
        this.optimalRangeModifier = optimalRangeModifier;
        this.maximumRangeModifier = maximumRangeModifier;
    }

    public int getMinimumRangeModifier() {
        return minimumRangeModifier;
    }

    public int getOptimalRangeModifier() {
        return optimalRangeModifier;
    }

    public int getMaximumRangeModifier() {
        return maximumRangeModifier;
    }

}
