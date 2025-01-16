package org.example.repository;

import org.example.model.Owner;
import org.example.model.Pet;
import org.example.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static void updatePet(Pet pet) {
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ptsm = createOwnerPreparedStatementUpdatePet(conn, pet)) {
            ptsm.executeUpdate();
            System.out.printf("Pet updated successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating Pet" + e.getMessage());
        }
    }

    public static PreparedStatement createOwnerPreparedStatementUpdatePet(Connection conn, Pet pet) throws SQLException {
        String query = "UPDATE pet SET name = ?, species = ?, breed = ? , age = ? WHERE (pet_id = ?)";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setString(1, pet.getName());
        pstm.setString(2, pet.getSpecies());
        pstm.setString(3, pet.getBreed());
        pstm.setInt(4, pet.getAge());
        pstm.setInt(5, pet.getId());
        return pstm;
    }

    //Retornando um optional porque vamos fazer uma busca que não sabemos se vai ou não retornar algo
    public static Optional<Pet> findById(int id) {
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ptsm = createOwnerPreparedStatementFindById(conn, id);
            ResultSet rs = ptsm.executeQuery()) {
            if(!rs.next()) return Optional.empty();//Se não tiver nada na o banco, retorne um optional vazio
            Owner owner = Owner.builder()
                    .id(rs.getInt("ow_id"))
                    .name(rs.getString("owner_name"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .build();

            return Optional.of(//Optional.of() cria um valor
                    Pet.builder()
                            .id(rs.getInt("pet_id"))
                            .name(rs.getString("name"))
                            .species(rs.getString("species"))
                            .breed(rs.getString("breed"))
                            .age(rs.getInt("age"))
                            .owner(owner)
                            .build()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding Pet " + e.getMessage());
        }
    }

    public static PreparedStatement createOwnerPreparedStatementFindById(Connection conn, int id) throws SQLException {
        String query = "SELECT p.pet_id, p.name, p.species, p.breed, p.age, o.id AS ow_id, o.name AS owner_name, o.email, o.phone " +
                "FROM pet p " +
                "INNER JOIN owner o " +
                "ON p.owner_id = o.id " +
                "WHERE p.pet_id = ?";
        PreparedStatement pstm = conn.prepareStatement(query);
        pstm.setInt(1, id);
        return pstm;
    }

    public static List<Pet> findPetByName(String name){
        List<Pet> pets = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ptsm = createPetPreparedStatementFindByName(conn, name);
            ResultSet rs = ptsm.executeQuery()) {
            while(rs.next()){
                Owner owner = Owner.builder()
                        .id(rs.getInt("ow_id"))
                        .name(rs.getString("owner_name"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .build();

                Pet pet = Pet.builder()
                        .id(rs.getInt("pet_id"))
                        .name(rs.getString("name"))
                        .species(rs.getString("species"))
                        .breed(rs.getString("breed"))
                        .age(rs.getInt("age"))
                        .owner(owner)
                        .build();
                pets.add(pet);
                //System.out.printf("Pet found successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while founding Pet " + e.getMessage());
        }
        return pets;
    }

    public static PreparedStatement createPetPreparedStatementFindByName(Connection conn, String name) throws SQLException {
        String query = "SELECT p.pet_id, p.name, p.species, p.breed, p.age, o.id AS ow_id, o.name AS owner_name, o.email, o.phone " +
                "FROM pet p " +
                "INNER JOIN owner o " +
                "ON p.owner_id = o.id " +
                "WHERE p.name like ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, "%" + name + "%");
        return ps;
    }

}
