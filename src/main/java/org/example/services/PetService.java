package org.example.services;

import org.example.dto.OwnerDTO;
import org.example.dto.PetDTO;
import org.example.mapper.PetMapper;
import org.example.model.OwnerModel;
import org.example.model.PetModel;
import org.example.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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

    public PetDTO findById(Long id){
        Optional<PetModel> pet = petRepository.findById(id);
        return pet.map(petMapper::mapToDTO).orElse(null);
    }

    public PetDTO save(PetDTO pet){
        PetModel petModel = petRepository.save(petMapper.mapToModel(pet));
        return petMapper.mapToDTO(petModel);
    }
}
