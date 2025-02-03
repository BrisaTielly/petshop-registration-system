package org.example.controller;

import org.example.dto.OwnerDTO;
import org.example.model.OwnerModel;
import org.example.services.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<OwnerDTO>> findAll() {
       List<OwnerDTO> owners = ownerService.findAll();
       return ResponseEntity.ok(owners);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<OwnerDTO> findById(@PathVariable Long id) {
        OwnerDTO ownerDTO = ownerService.findById(id);
        return ResponseEntity.ok(ownerDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<OwnerDTO> save(@RequestBody OwnerDTO ownerDTO){
        OwnerDTO owner = ownerService.save(ownerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(owner);
    }
}
