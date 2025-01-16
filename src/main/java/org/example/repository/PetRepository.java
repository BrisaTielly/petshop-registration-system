package org.example.repository;

import org.example.model.Pet;
import org.example.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PetRepository {

    public static void registerPet(Pet pet) {
        try(Connection conn = ConnectionFactory.getConnection()
            ;PreparedStatement pstm = createPetPreparedStatement(conn, pet);){
            pstm.executeUpdate();
            System.out.println("Pet registered successfully");
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error while inserting pet");
        }
    }
    public static PreparedStatement createPetPreparedStatement(Connection conn,Pet pet) throws SQLException {
        String query = "INSERT INTO pet (name, species, breed, age, owner_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, pet.getName());
        pstm.setString(2, pet.getSpecies());
        pstm.setString(3, pet.getBreed());
        pstm.setInt(4, pet.getAge());
        pstm.setInt(5, pet.getOwner().getId());
        return pstm;
    }

    public static void deletePet(int id) {
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ptsm = createOwnerPreparedStatementDeletePet(conn, id)) {
            ptsm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting Pet: " + e.getMessage());
        }

    }

    public static PreparedStatement createOwnerPreparedStatementDeletePet(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM pet WHERE pet_id = ?";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setInt(1, id);
        return pstm;
    }

}
