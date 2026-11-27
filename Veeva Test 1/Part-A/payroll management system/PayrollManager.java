import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PayrollManager {
    private List<Employee> list = new ArrayList<>();

    public void addEmployee(Employee emp) {
        list.add(emp);
    }

    public Map<String, Double> getDepartmentWiseTotalSalary() {
        return list.stream()
                .collect(Collectors.groupingBy(
                        emp -> emp.getDepartment(),
                        Collectors.summingDouble(emp -> emp.calculateSalary())
                ));
    }

    public List<String> getTopEarners(double threshold) {
        return list.stream()
                .filter(emp -> emp.calculateSalary() > threshold)
                .map(emp -> emp.getName())
                .sorted()
                .collect(Collectors.toList());
    }
}
