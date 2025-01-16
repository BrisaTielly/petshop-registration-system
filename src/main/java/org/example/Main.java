package org.example;

import org.example.services.OwnerService;
import org.example.services.PetService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            showMainMenu();
            option = readInt(scanner, "Please enter a valid option: ");

            if (option == 0) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            }

            switch (option) {
                case 1 -> handleOwnerMenu(scanner);
                case 2 -> handlePetMenu(scanner);
                default -> System.out.println("[ERROR] Invalid option. Please try again.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n==============================");
        System.out.println("          Main Menu           ");
        System.out.println("==============================");
        System.out.println("1. Owner Management");
        System.out.println("2. Pet Management");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private static void handleOwnerMenu(Scanner scanner) {
        showOwnerMenu();
        int option = readInt(scanner, "Please enter a valid option: ");

        if (option == 9) return;

        try {
            OwnerService.menu(option);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void showOwnerMenu() {
        System.out.println("\n==============================");
        System.out.println("        Owner Menu            ");
        System.out.println("==============================");
        System.out.println("1. Save Owner");
        System.out.println("2. Delete Owner");
        System.out.println("3. Update Owner");
        System.out.println("4. Search Owner");
        System.out.println("9. Go Back");
        System.out.print("Select an option: ");
    }

    private static void handlePetMenu(Scanner scanner) {
        showPetMenu();
        int option = readInt(scanner, "Please enter a valid option: ");

        if (option == 9) return;

        try {
            PetService.menu(option);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void showPetMenu() {
        System.out.println("\n==============================");
        System.out.println("         Pet Menu             ");
        System.out.println("==============================");
        System.out.println("1. Save Pet");
        System.out.println("2. Delete Pet");
        System.out.println("3. Update Pet");
        System.out.println("4. Search Pet");
        System.out.println("9. Go Back");
        System.out.print("Select an option: ");
    }

    private static int readInt(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
}
