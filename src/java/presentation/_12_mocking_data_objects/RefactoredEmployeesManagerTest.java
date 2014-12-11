package presentation._12_mocking_data_objects;

import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class RefactoredEmployeesManagerTest {

    private final EmployeesManager employeesManager = new EmployeesManager();

    @Test
    public void sumsSalaryForAllEmployees() {
        // given
        Employee employeeOne = new Employee("Employee Name One", 25000.00);
        Employee employeeTwo = new Employee("Employee Name Two", 38000.00);
        List<Employee> employees = newArrayList(employeeOne, employeeTwo);

        // when
        double sum = employeesManager.sumSalaries(employees);

        // then
        assertThat(sum, is(equalTo(63000.00)));
    }

}
