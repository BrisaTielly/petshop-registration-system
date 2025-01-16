package org.example.services;

import org.example.model.Owner;
import org.example.repository.OwnerRepository;

import java.util.Optional;
import java.util.Scanner;

public class OwnerService {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menu(int option) {
        switch (option) {
            case 1:
                registerOwner();
                break;
            case 2:
                deleteOwner();
                break;
            case 3:
                updateOwner();
                break;
            case 4:
                findOwnerByName();
                break;
            default:
                System.out.println("\n[ERROR] Invalid option! Please select a valid option.");
        }
    }

    public static void registerOwner() {
        System.out.println("\n==============================");
        System.out.println("  Register Owner");
        System.out.println("==============================");
        System.out.print("Enter owner name: ");
        String name = scanner.nextLine();
        System.out.print("Enter owner email: ");
        String email = scanner.nextLine();
        System.out.print("Enter owner phone: ");
        String phone = scanner.nextLine();

        Owner owner = Owner.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .build();
        register(owner);
    }

    public static void deleteOwner() {
        System.out.println("\n==============================");
        System.out.println("  Delete Owner");
        System.out.println("==============================");
        System.out.print("Enter owner id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        validateId(id);
        OwnerRepository.deleteOwner(id);
        System.out.println("\n[INFO] Owner deleted successfully!");
    }

    public static void updateOwner() {
        System.out.println("\n==============================");
        System.out.println("  Update Owner");
        System.out.println("==============================");
        System.out.print("Enter owner id: ");
        Optional<Owner> owner = OwnerRepository.findById(Integer.parseInt(scanner.nextLine()));
        if (owner.isEmpty()) {
            System.out.println("[ERROR] Owner not found!");
            return;
        }
        Owner ownerdb = owner.get();
        System.out.println("Owner found: " + ownerdb);

        System.out.print("Enter owner name (leave empty to keep current): ");
        String name = scanner.nextLine();
        name = name.isEmpty() ? ownerdb.getName() : name;

        System.out.print("Enter owner email (leave empty to keep current): ");
        String email = scanner.nextLine();
        email = email.isEmpty() ? ownerdb.getEmail() : email;

        System.out.print("Enter owner phone (leave empty to keep current): ");
        String phone = scanner.nextLine();
        phone = phone.isEmpty() ? ownerdb.getPhone() : phone;

        Owner ownerModified = Owner.builder()
                .id(ownerdb.getId())
                .name(name)
                .email(email)
                .phone(phone.trim())
                .build();
        OwnerRepository.updateOwner(ownerModified);
        System.out.println("\n[INFO] Owner updated successfully!");
    }

    public static void findOwnerByName() {
        System.out.println("\n==============================");
        System.out.println("  Find Owner By Name");
        System.out.println("==============================");
        System.out.print("Enter owner name: ");
        String name = scanner.nextLine();
        System.out.println("\n[INFO] Owners found: ");
        OwnerRepository.findOwnerByName(name).forEach(System.out::println);
    }

    public static void register(Owner owner) {
        validateOwner(owner);
        OwnerRepository.registerOwner(owner);
        System.out.println("\n[INFO] Owner registered successfully!");
    }

    public static void validateOwner(Owner owner) {
        validateName(owner.getName());
        validateEmail(owner.getEmail());
    }

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty!");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    public static void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id. Must be a positive number.");
        }
    }
}
