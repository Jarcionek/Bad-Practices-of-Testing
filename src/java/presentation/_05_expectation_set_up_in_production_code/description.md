## Expectation set up in production code

Iterating over enum in production code to validate a string.


### Domain:

In a web browser game, where the player controls a group of soldiers, player can give orders to the soldiers. The order is included in the URL of the request made by the browser. We want to validate incoming request, valid soldier's actions are move, crouch and attack.


### Test code:

```java
@Test
public void testValidator() {
    for (Action action : Action.values()) {
        assertTrue(action.toString(), actionValidator.isValidAction(action.toString()));
    }
}
```


### Production code:

```java
public enum Action {
    MOVE,
    TURN,
    ATTACK
}

public class ActionValidator {
    public boolean isValidAction(String action) {
        try {
            Action.valueOf(action);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
```


### Problem:

The test is using production code as its expectation. The test tests implementation, not behaviour. Behaviour is that strings “MOVE”, “TURN” and “ATTACK” are valid but whether we represent it internally as an enum or not, this test should not care about. What if we change the implementation to use a switch over string? Then we will run code analyzer and notice that none of the three enum values are used so will remove them as well. At this point the above test will be always green because the loop will not iterate at all, and the only usage of this enum will be in the test.

There are also three other problems:

1. there is no test checking the false value, implementation that always returns true would make the test pass
2. the test will fail if I override toString() method on that enum (another reason for failure)
3. if I do so, diagnostics will be poor - it will not be shown what the actual input value was

And all these problems are of course on top of loop in the test, meaningless tests’ names, poor diagnostics and inability to debug easily.


### Solution:

Test behaviour, not implementation. Behaviour is that strings “MOVE”, “TURN” and “ATTACK” are valid and everything else is invalid, so these four tests will do:

```java
@Test
public void acceptsMove() {
    assertThat(actionValidator.isValidAction("MOVE"), is(equalTo(true)));
}

@Test
public void acceptsTurn() {
    assertThat(actionValidator.isValidAction("TURN"), is(equalTo(true)));
}

@Test
public void acceptsAttack() {
    assertThat(actionValidator.isValidAction("ATTACK"), is(equalTo(true)));
}

@Test
public void doesNotAcceptInvalidAction() {
    assertThat(actionValidator.isValidAction("CROUCH"), is(equalTo(false)));
}
```
