package org.example.services;

import org.example.model.Owner;
import org.example.repository.OwnerRepository;

public class OwnerService {
    public static void register(Owner owner) {
        OwnerRepository.registerOwner(owner);
    }

    public static void validateOwner(Owner owner) {
        validateName(owner.getName());
        validatEmail(owner.getEmail());
    }

    public static void validateName(String name) {
        PetService.validateName(name);
    }

    public static void validatEmail(String email) {
        if( email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

}
