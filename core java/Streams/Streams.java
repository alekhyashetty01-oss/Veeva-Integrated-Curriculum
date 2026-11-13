import java.util.*;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> items = Arrays.asList(
                "Java", "Python", "JavaScript", "Scala", "Solana", "Schema",
                "MySQL", "MongoDB", "PostgreSQL",
                "Windows", "Linux", "MacOS", "Solaris",
                "ChatGPT", "Selenium", "Swift", "Java"
        );
        System.out.println("Available Items: " + items);
        System.out.print("Enter a letter: ");
        String letter = sc.nextLine().trim();

        // 1. Filter all items that start with the input letter (case-insensitive)[cite: 4]
        List<String> task1 = items.stream()
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\n1. Starts with letter: " + task1);

        // 2. Filter items that start with the given letter and also end with 'a'[cite: 4]
        List<String> task2 = items.stream()
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()) && name.toLowerCase().endsWith("a"))
                .collect(Collectors.toList());
        System.out.println("2. Starts with letter and ends with 'a': " + task2);

        // 3. Filter items starting with the letter and convert each item to UPPERCASE using map()[cite: 4]
        List<String> task3 = items.stream()
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("3. Uppercase: " + task3);

        // 4. Combine all matching items into a single string separated by commas using joining()[cite: 4]
        String task4 = items.stream()
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
                .collect(Collectors.joining(", "));
        System.out.println("4. Single output: " + task4);

        // 5. Convert to uppercase and remove duplicate entries using distinct()[cite: 4]
        List<String> task5 = items.stream()
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
                .map(name -> name.toUpperCase())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("5. Uppercase distinct: " + task5);

        // 6. Sort the matching items in alphabetical order (ignoring uppercase/lowercase differences)[cite: 4]
        List<String> task6 = items.stream()
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
        System.out.println("6. Sorted: " + task6);

        // 7. Combine distinct, uppercase conversion, and natural sorting together[cite: 4]
        List<String> task7 = items.stream()
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
                .map(name -> name.toUpperCase())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("7. Distinct uppercase sorted: " + task7);

        // 8. Count the total number of unique matching items using count()[cite: 4]
        long task8 = items.stream()
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
                .distinct()
                .count();
        System.out.println("8. Count of distinct: " + task8);

        // 9. Fetch only the very first matching element wrapped in an Optional[cite: 5]
        Optional<String> task9 = items.stream()
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
                .findFirst();
        System.out.println("9. First element: " + task9.orElse("None"));

        // 10. Check if AT LEAST ONE item starts with the given letter (returns true or false)[cite: 5]
        boolean task10 = items.stream()
                .anyMatch(name -> name.toLowerCase().startsWith(letter.toLowerCase()));
        System.out.println("10. Any match: " + task10);

        // 11. Check if EVERY item in the entire list starts with the given letter (returns true or false)[cite: 5]
        boolean task11 = items.stream()
                .allMatch(name -> name.toLowerCase().startsWith(letter.toLowerCase()));
        System.out.println("11. All match: " + task11);
        sc.close();
    }
}