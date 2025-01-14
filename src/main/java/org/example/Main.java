package org.example;

import org.example.repository.ProducerDAO;
import org.example.model.Producer;
import org.example.util.ConnectionFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println(ConnectionFactory.getConnection());
        }
}