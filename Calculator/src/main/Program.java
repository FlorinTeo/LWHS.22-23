package main;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        System.out.println("Welcome to Smart Calculator!");
        NumCalc numCalc = new NumCalc();

        Scanner input = new Scanner(System.in);
        do {
            System.out.printf("Expression?> ");
            String line = input.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                break;
            }
            
            try {
                String result = numCalc.evaluate(line);
                System.out.printf("%s\n", result);
            } catch (Exception e) {
                System.out.println();
                System.out.printf("##Error: %s\n", e.getMessage());
                System.out.printf("##Stack: ----------------\n");
                e.printStackTrace();
                System.out.println();
            }

        } while (true);

        System.out.println("Goodbye!");
        input.close();
    }

}
