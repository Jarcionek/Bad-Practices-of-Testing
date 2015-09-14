package presentation._09_test_verifying_implementation_rather_than_behaviour;

public class SalaryRange {

    private final int min;
    private final int max;

    public SalaryRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

}
