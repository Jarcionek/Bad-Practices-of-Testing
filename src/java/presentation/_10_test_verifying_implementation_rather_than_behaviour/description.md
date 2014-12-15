## Testing implementation instead of behaviour


### Domain:

HR in large corporation needs some tools to filter employees based on different criteria such as salary range. This is a class for finding employees with other "find" methods (and their tests) removed.


### Test Code:

```java
public class EmployeeFinderTest {

    private final Employee employeeOne = new Employee("One", 20_000);
    private final Employee employeeTwo = new Employee("Two", 25_000);
    private final Employee employeeThree = new Employee("Three", 30_000);
    private final Employee employeeFour = new Employee("Four", 45_000);
    private final List<Employee> employees = asList(employeeOne, employeeTwo, employeeThree, employeeFour);

    private EmployeeFinder employeeFinder = new EmployeeFinder();

    @Test
    public void test() {
        List<Employee> actual = employeeFinder.findEmployeesWithSalaryInRange(employees, new SalaryRange(27_000, 32_000));

        assertThat(actual, is(sameBeanAs(asList(employeeThree))));
    }

    @Test
    public void testGreaterThanMinimum() {
        assertTrue(employeeFinder.employeeHasSalaryInRange(employeeTwo, new SalaryRange(25_000, 50_000)));
    }

    @Test
    public void testLowerThanMaximum() {
        assertTrue(employeeFinder.employeeHasSalaryInRange(employeeThree, new SalaryRange(10_000, 30_000)));
    }

}
```


### Production code:

```java
public class EmployeeFinder {

    public List<Employee> findEmployeesWithSalaryInRange(List<Employee> employeesToSearch, SalaryRange range) {
        List<Employee> foundEmployees = new ArrayList<>();
        for (Employee employee : employeesToSearch) {
            if (employeeHasSalaryInRange(employee, range)) {
                foundEmployees.add(employee);
            }
        }
        return foundEmployees;
    }

    @VisibleForTesting
    public boolean employeeHasSalaryInRange(Employee employee, SalaryRange range) {
        return employee.getSalary() >= range.getMin() && employee.getSalary() <= range.getMax();
    }

}
```


### Problem:

Firstly, test code in production - increasing the access of the method or annotating a method with ```@VisibleForTesting``` only for the purpose of testing is a test code. Code which is used only in tests is actually unused and should be deleted - the less code to maintain the better.

Secondly, there is no guarantee that the actual method under test (```findEmployeesWithSalaryInRange```) is using method ```employeeHasSalaryInRange```. If I inlined the method and accidentally changed the sign from ```<=``` to ```<```, no test would fail:

```java
public class EmployeeFinder {

    public List<Employee> findEmployeesWithSalaryInRange(List<Employee> employeesToSearch, SalaryRange range) {
        List<Employee> foundEmployees = new ArrayList<>();
        for (Employee employee : employeesToSearch) {
            if (employee.getSalary() >= range.getMin() && employee.getSalary() < range.getMax()) {
                foundEmployees.add(employee);
            }
        }
        return foundEmployees;
    }

    @VisibleForTesting
    public boolean employeeHasSalaryInRange(Employee employee, SalaryRange range) {
        return employee.getSalary() >= range.getMin() && employee.getSalary() <= range.getMax();
    }

}
```

Thirdly, two tests for method ```employeeHasSalaryInRange``` always expect the same output. The simplest implementation is to always return true in this method.

Finally the tests names are not meaningful.

Many of these problems were caused by the fact that this implementation was not test driven. Developer first wrote a method ```employeeHasSalaryInRange``` and then used it in the main "find" method.


### Solution:

Test behaviour, not implementation. All tests should be calling only method ```findEmployeesWithSalaryInRange```. Whether this method has logic extracted to private method, is using static methods from some other class or is using third party libraries, is not relevant. The method is supposed to return employees with salary in requested range, no matter how.

If a piece of logic is so large that you need to extract it to a method and test is separately, extract it to another class and make it dependency of this class - you will be able to mock it and not care about what it does, because it has its own unit tests.


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_11_swallowing_assertion_error/description.md)
