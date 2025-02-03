package org.example.mapper;

import org.example.dto.OwnerDTO;
import org.example.model.OwnerModel;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {

    public OwnerModel mapToModel(OwnerDTO ownerDTO){
        OwnerModel ownerModel = new OwnerModel();
        ownerModel.setId(ownerDTO.getId());
        ownerModel.setName(ownerDTO.getName());
        ownerModel.setEmail(ownerDTO.getEmail());
        ownerModel.setPhone(ownerDTO.getPhone());
        ownerModel.setPets(ownerDTO.getPets());
        return ownerModel;
    }

    public OwnerDTO mapToDTO(OwnerModel ownerModel){
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(ownerModel.getId());
        ownerDTO.setName(ownerModel.getName());
        ownerDTO.setEmail(ownerModel.getEmail());
        ownerDTO.setPhone(ownerModel.getPhone());
        ownerDTO.setPets(ownerModel.getPets());
        return ownerDTO;
    }
}
