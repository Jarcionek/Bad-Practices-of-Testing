package presentation._11_swallowing_assertion_error;

import org.junit.Test;

public class Refactored_2_CacheTest {

    private final String request = "abc-123";

    private final Cache cache = new Cache();

    @Test(expected = DbError.class)
    public void throwsDbErrorWhenValueIsNotCachedAndDbIsDown() {
        cache.fetch(request);
    }

}
