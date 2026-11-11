import java.util.Scanner;
import java.util.stream.IntStream;

public class prime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter limit N: ");
        int n = sc.nextInt();

        System.out.print("Prime numbers up to " + n + ": ");
        IntStream.rangeClosed(2, n)
                .filter(num -> IntStream.rangeClosed(2, (int) Math.sqrt(num))
                        .noneMatch(i -> num % i == 0))
                .forEach(p -> System.out.print(p + " "));

        sc.close();
    }
}