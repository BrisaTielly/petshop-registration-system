package org.example.repository;

import org.example.model.Producer;
import org.example.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProducerDAO {

    public static void insertProducer(Producer producer){
        String sql = "INSERT INTO producers (producerName) VALUES(?)";
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, producer.getName());
            pstm.executeUpdate();
            System.out.println("Inserido com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir produtor no banco de dados.");
        }
    }

    public static void deleteProducer(int id){
        String sql = "DELETE FROM producers WHERE id = ?";
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql);){
            pstm.setInt(1, id);
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar produtor no banco de dados.");
        }
    }

    public static void updateProducer(Producer producer){
        String sql = "UPDATE producers SET producerName = ? WHERE id = ?";
        try(Connection conn = ConnectionFactory.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, producer.getName());
            pstm.setInt(2, producer.getId());
            pstm.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar produtor no banco de dados.");
        }
    }

    public static List<Producer> findAll(){
        return findByName("");
    }

    public static List<Producer> findByName(String name){
        String sql = "SELECT producerName, id FROM producers WHERE producerName like ?";
        List<Producer> producers = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql); ResultSet rs = pstm.executeQuery()){
            pstm.setString(1, "%"+name+"%");
            while (rs.next()){
                Producer producer = Producer
                        .ProducerBuilder
                        .builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("producerName"))
                        .build();
                producers.add(producer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return producers;
    }

   public static void metaData(){
        String sql = "SELECT * FROM producers";
        try(Connection conn = ConnectionFactory.getConnection()
            ;PreparedStatement pstm = conn.prepareStatement(sql)
            ;ResultSet rs = pstm.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnType = rsmd.getColumnTypeName(i);
                    System.out.println(columnName + " " + columnType);
                }


        }catch (SQLException e){
            e.printStackTrace();
        }
   }
}
