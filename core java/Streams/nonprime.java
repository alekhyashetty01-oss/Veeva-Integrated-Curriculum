import java.util.Scanner;
import java.util.stream.IntStream;

public class nonprime {

    public static boolean isNonPrime(int num) {
        if (num <= 1) return true;
        return IntStream.rangeClosed(2, (int) Math.sqrt(num))
                .anyMatch(i -> num % i == 0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter limit N: ");
        int n = sc.nextInt();

        System.out.print("Non-prime numbers from 1 to " + n + ": ");
        IntStream.rangeClosed(1, n)
                .filter(nonprime::isNonPrime)
                .forEach(p -> System.out.print(p + " "));

        sc.close();
    }
}