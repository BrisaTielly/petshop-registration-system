package org.example;

import org.example.model.Pet;
import org.example.repository.ProducerDAO;
import org.example.model.Producer;
import org.example.services.OwnerService;
import org.example.services.PetService;
import org.example.util.ConnectionFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.printf("Digite a opção: ");
//        int opt = scanner.nextInt();
//        OwnerService.menu(opt);
        PetService.menu(2);
    }
}