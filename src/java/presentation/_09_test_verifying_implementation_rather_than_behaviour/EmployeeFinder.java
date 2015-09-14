package presentation._09_test_verifying_implementation_rather_than_behaviour;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

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

    // other "find" methods

}
