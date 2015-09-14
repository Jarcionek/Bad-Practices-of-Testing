## Mocking data objects

### Test code:

```java
public class EmployeesManagerTest {

    private final EmployeesManager employeesManager = new EmployeesManager();

    @Test
    public void sumsSalaryForAllEmployees() {
        // given
        Employee employeeOne = mock(Employee.class);
        when(employeeOne.getSalary()).thenReturn(25000.00);

        Employee employeeTwo = mock(Employee.class);
        when(employeeTwo.getSalary()).thenReturn(38000.00);

        List<Employee> employees = newArrayList(employeeOne, employeeTwo);

        // when
        double sum = employeesManager.sumSalaries(employees);

        // then
        assertThat(sum, is(equalTo(63000.00)));
    }

}
```


### Problem:

Employee is a data object, we don’t write tests for getters/setters, we usually test data objects indirectly. However, if `Employee` class looks like this:

```java
public class Employee {

    // fields, constructors

    public double getSalary() {
        return 0;
    }

    // other getters

}
```

This test will be green. If there is no other test using Employee class, you will have failing acceptance test and it may be difficult to figure out what the problem is. Unless you want to write tests for getters and setters.

Another problem here is that salary is expressed using doubles which cannot precisely represent certain values. E.g. `5.6 + 5.8` will give `11.399999999` - if expected value was `11.4`, the test would be failing.


### Solution:

Never mock data objects. Mock only methods that have unit tests.

Use `BigDecimal` for floating point numbers when exact precision is needed. In this case expressing a salary in pennies as `int` would be sufficient.


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_13_testing_what_the_code_does_not_do/description.md)
