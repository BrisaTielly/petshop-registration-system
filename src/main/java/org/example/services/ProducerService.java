package org.example.services;

import org.example.repository.ProducerDAO;
import org.example.model.Producer;
import java.util.List;

//Verificações e aplicação de fato dos serviços
public class ProducerService {

    public static void registerProducer(Producer producer) {
        ProducerDAO.insertProducer(producer);
    }

    public static void deleteProducer(Integer id) {
       validateId(id);
        ProducerDAO.deleteProducer(id);
    }

    public static void updateProducer(Producer producer) {
        validateId(producer.getId());
        ProducerDAO.updateProducer(producer);
    }

    public static List<Producer> findAll(){
        return ProducerDAO.findAll();
    }

    public static List<Producer> findByName(String name) {
        return ProducerDAO.findByName(name);
    }

    public static void metaData() {
        ProducerDAO.metaData();
    }

    public static void validateId(Integer id) {
        if (id == null || id <= 0) throw new IllegalArgumentException("id must be greater than 0");
    }
}
