import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    private static FiboCache _fiboCache = new FiboCache(1000);
    
    private static long fibonacci(int n) {
        return n < 2
            ? n
            : fibonacci(n-1) + fibonacci(n-2);
    }
    
    private static long fastFibonacci(int n) {
        Long fiboValue = _fiboCache.retrieveFibo(n);
        if (fiboValue != null) {
            return fiboValue;
        } else {
            fiboValue = n < 2
                    ? n
                    : fastFibonacci(n-1) + fastFibonacci(n-2);
            _fiboCache.cacheFibo(n, fiboValue);
            return fiboValue;
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Fibonacci calculator!");
        Scanner console = new Scanner(System.in);
        
        do {
            System.out.printf("Enter number> ");
            String line = console.nextLine();
            if (line.equalsIgnoreCase("quit")) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }
            int n = Integer.parseInt(line);
            System.out.printf("%d\n",fastFibonacci(n));
            System.out.println(_fiboCache);
        } while(true);
        
        console.close();
        System.out.println("Goodbye!");
    }

}
