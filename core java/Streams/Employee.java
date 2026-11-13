import java.util.*;
import java.util.stream.Collectors;
public class Employee {
    static class Emp {
        String name;
        int age;
        String department;
        public Emp(String name, int age, String department) {
            this.name = name;
            this.age = age;
            this.department = department;
        }
        @Override
        public String toString() {
            return name + " (" + age + " yrs, " + department + ")";
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Emp> employees = new ArrayList<>();
        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= n; i++) {
            System.out.println("\nEnter details for Employee " + i + ":");
            System.out.print("Name: ");
            String name = sc.nextLine().trim();
            System.out.print("Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Department: ");
            String department = sc.nextLine().trim();

            employees.add(new Emp(name, age, department));
        }
        Map<String, List<Emp>> groupByDept = employees.stream()
                .collect(Collectors.groupingBy(e -> e.department));
        System.out.println("(i) Group by Department:\n" + groupByDept);

        Map<Integer, List<Emp>> groupByAge = employees.stream()
                .collect(Collectors.groupingBy(e -> e.age));
        System.out.println("\n(ii) Group by Age:\n" + groupByAge);

        Optional<Emp> youngest = employees.stream()
                .min(Comparator.comparingInt(e -> e.age));
        Optional<Emp> oldest = employees.stream()
                .max(Comparator.comparingInt(e -> e.age));

        System.out.println("\n(iii) Youngest and Oldest:");
        youngest.ifPresent(e -> System.out.println("  Youngest: " + e));
        oldest.ifPresent(e -> System.out.println("  Oldest: " + e));

        System.out.println("\n(iv) Employee with Least Age:");
        youngest.ifPresent(e -> System.out.println("  Least Age Employee: " + e));

        sc.close();
    }
}