import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegerStreamTasks{
    public static void printStats(String listName, List<Integer> list) {
        IntSummaryStatistics stats = list.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        System.out.println(listName + " Stats:");
        System.out.println("  Sum: " + stats.getSum());
        System.out.printf("  Average: %.2f\n", stats.getAverage());
        System.out.println("  Min: " + (list.isEmpty() ? "None" : stats.getMin()));
    }

    public static List<Integer> getKMostFrequent(List<Integer> list, int k) {
        return list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static List<Integer> readIntegerList(Scanner sc, String listLabel) {
        List<Integer> list = new ArrayList<>();
        System.out.print("Enter number of elements in " + listLabel + ": ");
        int count = sc.nextInt();

        System.out.println("Enter " + count + " integers (separated by space or enter):");
        for (int i = 0; i < count; i++) {
            list.add(sc.nextInt());
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list1 = readIntegerList(sc, "List 1");
        List<Integer> list2 = readIntegerList(sc, "List 2");

        System.out.print("\nEnter value for k (for k-most frequent elements): ");
        int k = sc.nextInt();

        System.out.println("\n--- Input Summary ---");
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);

        System.out.println("(i) Sum, Avg, and Min of each list:");
        printStats("List 1", list1);
        printStats("List 2", list2);

        System.out.println("\n(ii) " + k + "-Most Frequent Elements:");
        System.out.println("  List 1: " + getKMostFrequent(list1, k));
        System.out.println("  List 2: " + getKMostFrequent(list2, k));

        System.out.println("\n(iii) 2-Most Frequent Elements:");
        System.out.println("  List 1: " + getKMostFrequent(list1, 2));
        System.out.println("  List 2: " + getKMostFrequent(list2, 2));

        List<Integer> mergedList = Stream.of(list1, list2)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println("\n(iv) Merged List (using flatMap):");
        System.out.println("  " + mergedList);

        List<Integer> commonElements = list1.stream()
                .filter(list2::contains)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("\n(v) Common Elements in List 1 and List 2:");
        System.out.println("  " + commonElements);

        sc.close();
    }
}