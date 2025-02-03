package org.example.mapper;

import org.example.dto.PetDTO;
import org.example.model.PetModel;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    public PetModel mapToModel(PetDTO petDTO) {
        PetModel petModel = new PetModel();
        petModel.setId(petDTO.getId());
        petModel.setName(petDTO.getName());
        petModel.setAge(petDTO.getAge());
        petModel.setBreed(petDTO.getBreed());
        petModel.setSpecies(petDTO.getSpecies());
        petModel.setOwner(petDTO.getOwner());
        return petModel;
    }

    public PetDTO mapToDTO(PetModel petModel) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(petModel.getId());
        petDTO.setName(petModel.getName());
        petDTO.setAge(petModel.getAge());
        petDTO.setBreed(petModel.getBreed());
        petDTO.setSpecies(petModel.getSpecies());
        petDTO.setOwner(petModel.getOwner());
        return petDTO;
    }


}
