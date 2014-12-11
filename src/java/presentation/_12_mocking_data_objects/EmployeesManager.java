package presentation._12_mocking_data_objects;

import java.util.List;

public class EmployeesManager {

    public double sumSalaries(List<Employee> employees) {
        double total = 0;
        for (Employee employee : employees) {
            total += employee.getSalary();
        }
        return total;
    }

}
