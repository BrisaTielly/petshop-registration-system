package org.example;

import org.example.model.Pet;
import org.example.repository.ProducerDAO;
import org.example.model.Producer;
import org.example.services.OwnerService;
import org.example.services.PetService;
import org.example.util.ConnectionFactory;

public class Main {
    public static void main(String[] args) {
        OwnerService.menu(1);
    }
}