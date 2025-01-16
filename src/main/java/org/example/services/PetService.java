package org.example.services;

import org.example.model.Owner;
import org.example.model.Pet;
import org.example.repository.OwnerRepository;
import org.example.repository.PetRepository;

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
            case 3:
                updatePet();
                break;
            case 4:
                findPetByName();
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
        int age = readInt("Please enter a valid age: ");

        System.out.println("Enter the breed of the pet");
        String breed = scanner.nextLine();

        System.out.println("Enter the ID of the pet's owner");
        int owner_id = readInt("Please enter a valid owner ID: ");

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
        int id = readInt("Please enter a valid pet ID: ");
        OwnerService.validateId(id);
        PetRepository.deletePet(id);
        System.out.println("\n[INFO] Pet deleted successfully!");
    }

    public static void updatePet() {
        System.out.println("\n==============================");
        System.out.println("  Update Pet");
        System.out.println("==============================");
        System.out.print("Enter pet id: ");
        Optional<Pet> pet = PetRepository.findById(readInt("Please enter a valid pet ID: "));
        if (pet.isEmpty()) {
            System.out.println("[ERROR] Pet not found!");
            return;
        }
        Pet petDB = pet.get();
        System.out.println("Pet found: " + petDB);

        System.out.print("Enter pet name (leave empty to keep current): ");
        String name = scanner.nextLine();
        name = name.isEmpty() ? petDB.getName() : name;

        System.out.print("Enter pet species (leave empty to keep current): ");
        String species = scanner.nextLine();
        species = species.isEmpty() ? petDB.getSpecies() : species;

        System.out.print("Enter pet breed (leave empty to keep current): ");
        String breed = scanner.nextLine();
        breed = breed.isEmpty() ? petDB.getBreed() : breed;

        System.out.print("Enter pet age (leave empty to keep current): ");
        String ageInput = scanner.nextLine();
        int age = ageInput.isEmpty() ? petDB.getAge() : readInt("Please enter a valid age: ");

        Pet petMod = Pet.builder()
                .id(petDB.getId())
                .name(name)
                .species(species)
                .breed(breed)
                .age(age)
                .build();
        PetRepository.updatePet(petMod);
        System.out.println("\n[INFO] Pet updated successfully!");
    }

    public static void findPetByName() {
        System.out.println("\n==============================");
        System.out.println("  Find Pet By Name");
        System.out.println("==============================");
        System.out.print("Enter Pet name: ");
        String name = scanner.nextLine();
        System.out.println("\n[INFO] Pet found: ");
        PetRepository.findPetByName(name).forEach(System.out::println);
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

    private static int readInt(String errorMessage) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
}
