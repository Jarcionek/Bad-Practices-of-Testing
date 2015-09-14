
asserting multiple things (assert return value and verify notifier call)

- - - -

something with random number generator

- - - -

test AaaTest testing object Bbb
- to make it more difficult we could have Aaa in the setup in @Before method

- - - -

no assertion (i.e. incorrect one, always passing test)
- asserting on error message of an exception without fail statement

- - - -

expected exception thrown in "given" rather than in "when", using JUnit's "@Test(expected=...)"
- referring to 11 - swallowing assertion error

- - - -

asserting on default value returned by mocked object (and implementation making call with other parameters than mock was configured)
- referring to 14 - asserting on default value

- - - -

testing the setup of the test
- some fake service and test testing that game is disabled by calling isGameEnabled on this service

- - - -

something with train wreck
- like in Growing Object-Oriented Software Guided by Tests:
```java
((EditSaveCustomizer) master.getModelisable()
  .getDockablePanel()
    .getCustomizer())
      .getSaveItem().setEnabled(Boolean.FALSE.booleanValue());
```
- the fix needs a change of production code, not test code, so I am not sure if it fits well to this presentation

- - - -
