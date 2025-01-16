package org.example.services;

import org.example.model.Owner;
import org.example.model.Pet;
import org.example.repository.OwnerRepository;
import org.example.repository.PetRepository;
import org.example.util.ValidationUtils;

import java.util.Optional;
import java.util.Scanner;

public class PetService {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menu(int option) {
        switch (option) {
            case 1:
                registerPet();
                break;

            case 2:
                deletePet();
                break;

            default:
                throw new IllegalArgumentException("Invalid option");
        }
    }

    public static void registerPet() {
        System.out.println("Enter the name of the pet");
        String name = scanner.nextLine();
        System.out.println("Enter the species of the pet");
        String species = scanner.nextLine();
        System.out.println("Enter the age of the pet");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the breed of the pet");
        String breed = scanner.nextLine();
        System.out.println("Enter the ID of the pet's owner");
        int owner_id = Integer.parseInt(scanner.nextLine());
        //retorna um optional
        Optional<Owner> owner = OwnerRepository.findById(owner_id);
        if (owner.isEmpty()) {
            System.out.println("[ERROR] Owner not found!");
            return;
        }
        Owner ownerDB = owner.get();
        Pet pet = Pet.builder()
                .name(name)
                .species(species)
                .age(age)
                .breed(breed)
                .owner(ownerDB)
                .build();

        registerPet(pet);
    }

    public static void deletePet() {
        System.out.println("\n==============================");
        System.out.println("  Delete Pet");
        System.out.println("==============================");
        System.out.print("Enter pet id: ");
        int id = Integer.parseInt(scanner.nextLine());
        OwnerService.validateId(id);
        PetRepository.deletePet(id);
        System.out.println("\n[INFO] Pet deleted successfully!");
    }

    public static void registerPet(Pet pet) {
        validatePet(pet.getName(), pet.getSpecies(), pet.getAge());
        PetRepository.registerPet(pet);
    }

    public static void validatePet(String petName, String especies, int age) {
        validateName(petName);
        validateSpecies(especies);
        validateAge(age);
    }

    public static void validateName(String name) {
        if (name == null || name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("Name must be between 2 and 50 characters");
        }
        if (!name.matches("[A-Za-z\\s]+")) {
            throw new IllegalArgumentException("Name can only contain letters and spaces");
        }
    }


    public static void validateSpecies(String species) {
        if (species == null || species.isEmpty()) {
            throw new IllegalArgumentException("Species cannot be null or empty");
        }
    }

    public static void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }


}
