package org.example.services;

import org.example.dto.OwnerDTO;
import org.example.mapper.OwnerMapper;
import org.example.model.OwnerModel;
import org.example.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService {
   private final OwnerRepository ownerRepository;
   private final OwnerMapper ownerMapper;

    public OwnerService(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    @Transactional(readOnly = true)
    public List<OwnerDTO> findAll(){
       List<OwnerModel> owners =  ownerRepository.findAll();

       owners.forEach(owner -> owner.getPets().size()); //Forçando a Inicialização :(

       return owners.stream()
               .map(ownerMapper::mapToDTO)
               .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OwnerDTO findById(Long id){
        Optional<OwnerModel> owner = ownerRepository.findById(id);
        if(owner.get() != null){
            owner.get().getPets().size(); //forçando a inicialização
        }
        return owner.map(ownerMapper::mapToDTO).orElse(null);
    }

    public OwnerDTO save(OwnerDTO owner){
       OwnerModel ownerModel = ownerRepository.save(ownerMapper.mapToModel(owner));
       return ownerMapper.mapToDTO(ownerModel);
    }

    public OwnerDTO update(Long id, OwnerDTO ownerDTO) {
        Optional<OwnerModel> ownerModel = ownerRepository.findById(id);
        if(ownerModel.isPresent()){
            OwnerModel owner = ownerMapper.mapToModel(ownerDTO);
            owner.setId(id);
            owner = ownerRepository.save(owner);
            return ownerMapper.mapToDTO(owner);
        }
        return null;
    }

}
