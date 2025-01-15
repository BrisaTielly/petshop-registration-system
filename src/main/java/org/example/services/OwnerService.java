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


            default:
                System.out.println("Invalid option");
        }
    }

    public static void registerOwner() {
        System.out.print("Enter owner name: ");
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

    public static void deleteOwner() {
        System.out.print("\nEnter owner id : ");
        int id = scanner.nextInt();
        validateId(id);
        OwnerRepository.deleteOwner(id);
    }

    public static void updateOwner() {
        System.out.print("\nEnter owner id : ");
        Optional<Owner> owner = OwnerRepository.findById(scanner.nextInt());
        if (owner.isEmpty()) { //Se for vazio
            System.out.println("Not found");
            return;
        }
        //encontrou alguem pelo id e ja colocou em um objeto owner
        Owner ownerdb = owner.get(); //Pega o objeto de dentro o Optional
        System.out.println("Owner found: " + ownerdb);
        System.out.printf("Enter owner name if you want to update, or enter if you dont: ");
        String name = scanner.nextLine();
        name = (name.isEmpty()) ? ownerdb.getName() : name;
        System.out.printf("\nEnter owner phone if you want to update, or enter if you dont: ");
        String phone = scanner.nextLine();
        phone = (phone.isEmpty()) ? ownerdb.getPhone() : phone;
        System.out.printf("\nEnter owner email if you want to update, or enter if you dont: ");
        String email = scanner.nextLine();
        email = (email.isEmpty()) ? ownerdb.getEmail() : email;

        Owner ownerModified = Owner.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .build();
        OwnerRepository.updateOwner(ownerModified);
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

    public static void validateId(int id) {
        if(id < 0) {
            throw new IllegalArgumentException("Invalid id");
        }
    }

}
