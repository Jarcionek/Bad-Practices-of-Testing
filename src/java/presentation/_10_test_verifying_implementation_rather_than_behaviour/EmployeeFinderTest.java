package presentation._10_test_verifying_implementation_rather_than_behaviour;

import org.junit.Test;

import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

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

    // other tests

}
