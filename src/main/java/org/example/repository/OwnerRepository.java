package org.example.repository;

import org.example.model.Owner;
import org.example.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OwnerRepository {

    public static void registerOwner(Owner owner) {
        try(Connection conn = ConnectionFactory.getConnection()
            ;PreparedStatement pstm = createOwnerPreparedStatementRegisterOwner(conn, owner);){
            pstm.executeUpdate();
            System.out.println("Owner registered successfully");
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error while inserting Owner");
        }
    }

    public static PreparedStatement createOwnerPreparedStatementRegisterOwner(Connection conn, Owner owner) throws SQLException {
        String query = "INSERT INTO owner (id, name, phone, email) VALUES (?, ?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setInt(1, owner.getId());
        pstm.setString(2, owner.getName());
        pstm.setString(3, owner.getPhone());
        pstm.setString(4, owner.getEmail());
        return pstm;
    }

    public static void deleteOwner(int id) {
        String query = "DELETE FROM owner WHERE id = ?";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ptsm = createOwnerPreparedStatementDeleteOwner(conn, id)) {
            ptsm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting Owner");
        }

    }

    public static PreparedStatement createOwnerPreparedStatementDeleteOwner(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM owner WHERE id = ?";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setInt(1, id);
        return pstm;
    }


}
