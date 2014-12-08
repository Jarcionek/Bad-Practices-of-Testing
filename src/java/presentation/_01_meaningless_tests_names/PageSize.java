package presentation._01_meaningless_tests_names;

public class PageSize {

    private final int pageSize;

    public PageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public static PageSize pageSize(int pageSize) {
        return new PageSize(pageSize);
    }

    public int asInt() {
        return pageSize;
    }

}
