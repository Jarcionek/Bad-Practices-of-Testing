package presentation._09_test_verifying_implementation_rather_than_behaviour;

import org.junit.Test;

import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;

public class RefactoredEmployeeFinderTest {

    private final Employee employeeOne = new Employee("One", 20_000);
    private final Employee employeeTwo = new Employee("Two", 25_000);
    private final Employee employeeThree = new Employee("Three", 30_000);
    private final Employee employeeFour = new Employee("Four", 45_000);
    private final List<Employee> employees = asList(employeeOne, employeeTwo, employeeThree, employeeFour);

    private EmployeeFinder employeeFinder = new EmployeeFinder();

    @Test
    public void findsEmployeesWithSalaryGreaterThanMinimum() {
        List<Employee> actual = employeeFinder.findEmployeesWithSalaryInRange(employees, new SalaryRange(25_000, 50_000));

        assertThat(actual, is(sameBeanAs(asList(employeeTwo, employeeThree, employeeFour))));
    }

    @Test
    public void findsEmployeesWithSalaryLowerThanMaximum() {
        List<Employee> actual = employeeFinder.findEmployeesWithSalaryInRange(employees, new SalaryRange(10_000, 30_000));

        assertThat(actual, is(sameBeanAs(asList(employeeOne, employeeTwo, employeeThree))));
    }

    // other tests

}
