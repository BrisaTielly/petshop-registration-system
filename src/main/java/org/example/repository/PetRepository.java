package org.example.repository;

import org.example.model.Pet;
import org.example.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PetRepository {

    public static void registerPet(Pet pet) {
        String query = "INSERT INTO pet (name, species, breed, age, owner_id) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = ConnectionFactory.getConnection()
            ;PreparedStatement pstm = conn.prepareStatement(query)){
            pstm.setString(1, pet.getName());
            pstm.setString(2, pet.getSpecies());
            pstm.setString(3, pet.getBreed());
            pstm.setInt(4, pet.getOwner().getId());
            pstm.executeUpdate();
            System.out.println("Pet registered successfully");
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error while inserting pet");
        }
    }
}
