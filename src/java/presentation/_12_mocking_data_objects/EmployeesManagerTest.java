package presentation._12_mocking_data_objects;

import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
