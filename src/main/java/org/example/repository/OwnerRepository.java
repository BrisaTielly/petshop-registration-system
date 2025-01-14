package org.example.repository;

import org.example.model.Owner;
import org.example.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OwnerRepository {
    public static void registerOwner(Owner owner) {
        String query = "INSERT INTO owner (id, name, phone, email) VALUES (?, ?, ?, ?)";
        try(Connection conn = ConnectionFactory.getConnection()
            ; PreparedStatement pstm = conn.prepareStatement(query)){
            pstm.setInt(1, owner.getId());
            pstm.setString(2, owner.getName());
            pstm.setString(3, owner.getPhone());
            pstm.setString(4, owner.getEmail());
            pstm.executeUpdate();
            System.out.println("Owner registered successfully");
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error while inserting Owner");
        }
    }


}
