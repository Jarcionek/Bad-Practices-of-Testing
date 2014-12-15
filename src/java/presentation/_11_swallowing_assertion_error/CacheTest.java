package presentation._11_swallowing_assertion_error;

import org.junit.Test;

import static org.junit.Assert.fail;

public class CacheTest {

    private final String request = "abc-123";

    private final Cache cache = new Cache();

    @Test
    public void throwsDbErrorWhenValueIsNotCachedAndDbIsDown() {
        try {
            cache.fetch(request);
            fail();
        } catch (Error expected) {}
    }

    // other tests

}