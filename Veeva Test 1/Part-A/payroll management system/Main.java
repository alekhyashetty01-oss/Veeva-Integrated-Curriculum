import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayrollManager pm = new PayrollManager();
        System.out.print("enter total employees: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("type (1 for full time, 2 for contract): ");
            int type = sc.nextInt();
            System.out.print("id: ");
            int id = sc.nextInt();
            System.out.print("name: ");
            String name = sc.next();
            System.out.print("department: ");
            String dept = sc.next();
            if (type == 1) {
                System.out.print("base salary: ");
                double base = sc.nextDouble();
                System.out.print("bonus: ");
                double bonus = sc.nextDouble();
                pm.addEmployee(new FullTimeEmployee(id, name, dept, base, bonus));
            } else if (type == 2) {
                System.out.print("rate: ");
                double rate = sc.nextDouble();
                System.out.print("hours: ");
                int hours = sc.nextInt();
                pm.addEmployee(new ContractEmployee(id, name, dept, rate, hours));
            }
        }
        System.out.print("enter threshold: ");
        double threshold = sc.nextDouble();
        System.out.println("department total: " + pm.getDepartmentWiseTotalSalary());
        System.out.println("top earners: " + pm.getTopEarners(threshold));
    }
}