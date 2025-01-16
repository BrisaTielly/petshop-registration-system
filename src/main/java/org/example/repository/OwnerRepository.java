package org.example.repository;

import org.example.model.Owner;
import org.example.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static void updateOwner(Owner owner) {
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ptsm = createOwnerPreparedStatementUpdateOwner(conn, owner)) {
             ptsm.executeUpdate();
            System.out.printf("Owner updated successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating Owner" + e.getMessage());
        }
    }

    public static PreparedStatement createOwnerPreparedStatementUpdateOwner(Connection conn, Owner owner) throws SQLException {
        String query = "UPDATE owner SET name = ?, email = ?, phone = ? WHERE (id = ?)";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, owner.getName());
        pstm.setString(3, owner.getEmail());
        pstm.setString(2, owner.getPhone());
        pstm.setInt(4, owner.getId());
        return pstm;
    }

    //Retornando um optional porque vamos fazer uma busca que não sabemos se vai ou não retornar algo
    public static Optional<Owner> findById(int id) {
        String query = "SELECT id, name, email, phone FROM owner WHERE id = ?";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ptsm = createOwnerPreparedStatementFindById(conn, id);
            ResultSet rs = ptsm.executeQuery()) {
                if(!rs.next()) return Optional.empty(); //Se não tiver nada na o banco, retorne um optional vazio
                return Optional.of(//Optional.of() cria um valor
                        Owner.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .email(rs.getString("email"))
                                .phone(rs.getString("phone"))
                                .build()
                );
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding Owner");
        }
    }

    public static PreparedStatement createOwnerPreparedStatementFindById(Connection conn, int id) throws SQLException {
        String query = "SELECT id, name, phone, email  FROM owner WHERE id = ?";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setInt(1, id);
        return pstm;
    }

    public static List<Owner> findOwnerByName(String name){
        List<Owner> owners = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ptsm = createOwnerPreparedStatementFindByName(conn, name);
            ResultSet rs = ptsm.executeQuery()) {
            while(rs.next()){
                    Owner owner = Owner.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .email(rs.getString("email"))
                            .phone(rs.getString("phone"))
                            .build();
                    owners.add(owner);
                    //System.out.printf("Owner found successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while founding Owner " + e.getMessage());
        }
        return owners;
    }

    public static PreparedStatement createOwnerPreparedStatementFindByName(Connection conn, String name) throws SQLException {
        String query = "SELECT id, name, email, phone FROM owner WHERE name like ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, "%" + name + "%");
        return ps;
    }

}
