package main;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        System.out.println("Welcome to Smart Calculator!");

        Scanner input = new Scanner(System.in);
        do {
            System.out.printf("Expression?> ");
            String line = input.nextLine();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                break;
            }
        } while (true);

        System.out.println("Goodbye!");
        input.close();
    }

}
