package org.example.services;

import org.example.model.Owner;
import org.example.repository.OwnerRepository;

import java.util.Scanner;

public class OwnerService {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menu(int option) {
        switch (option) {
            case 1: registerOwner();
            break;
            case 2:
            default: System.out.println("Invalid option");
        }
    }

    public static void registerOwner() {
        System.out.print("\nEnter owner name: ");
        String name = scanner.nextLine();
        System.out.print("\nEnter owner email: ");
        String email = scanner.nextLine();
        System.out.print("\nEnter owner phone: ");
        String phone = scanner.nextLine();
        Owner owner = Owner
                .builder()
                .name(name)
                .phone(phone)
                .email(email)
                .build();
        register(owner);
    }

    public static void register(Owner owner) {
        validateOwner(owner);
        OwnerRepository.registerOwner(owner);
    }

    public static void validateOwner(Owner owner) {
        validateName(owner.getName());
        validateEmail(owner.getEmail());
    }

    public static void validateName(String name) {
        PetService.validateName(name);
    }

    public static void validateEmail(String email) {
        if( email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

}
