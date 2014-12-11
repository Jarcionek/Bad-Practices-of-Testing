## Swallowing assertion error

### Test code:

```java
@Test
public void throwsDbErrorWhenValueIsNotCachedAndDbIsDown() {
    try {
        cache.get(request);
        fail();
    } catch (Error expected) {}
}
```


### Problem:

DbError extends Error and so does AssertionError which is thrown by junit’s ```fail()``` method. Both errors will be caught in the “expected” catch statement. When you write this test for the first time (and when fetch method in cache does nothing) this test will go green straight away. This test does not test what it is supposed to test.

Another problem is that fail() gives poor diagnostics and all we will see is “java.lang.AssertionError” with stack trace.


### Solution:

1. Never catch more than you need to - change ```Error``` in catch block to ```DbError```
2. If you do not assert on exception’s message, use ```@Test(expected = DbError.class)``` instead of try-catch
3. Consider putting ```fail()``` after the catch block and putting ```return``` inside it
4. Always add a meaningful message to the ```fail()``` statement - here it could be e.g. “expected DbError but not thrown”
