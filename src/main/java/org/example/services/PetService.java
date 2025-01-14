package org.example.services;

import org.example.model.Pet;
import org.example.repository.PetRepository;

public class PetService {
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
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
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
