package org.example.services;

import org.example.dto.PetDTO;
import org.example.mapper.PetMapper;
import org.example.model.PetModel;
import org.example.repository.PetRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PetService {
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetService(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    public List<PetDTO> findAll(){
        List<PetModel> pets =  petRepository.findAll();
        return pets.stream()
                .map(petMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
