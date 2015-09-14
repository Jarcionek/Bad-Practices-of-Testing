package presentation._09_test_verifying_implementation_rather_than_behaviour;

import com.google.common.annotations.VisibleForTesting;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class EmployeeFinder {

    public List<Employee> findEmployeesWithSalaryInRange(List<Employee> employeesToSearch, SalaryRange range) {
        return employeesToSearch.stream()
                .filter(employee -> employeeHasSalaryInRange(employee, range))
                .collect(toList());
    }

    @VisibleForTesting
    public boolean employeeHasSalaryInRange(Employee employee, SalaryRange range) {
        return employee.getSalary() >= range.getMin() && employee.getSalary() <= range.getMax();
    }

    // other "find" methods

}
