package org.example.controller;

import org.example.dto.OwnerDTO;
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
    public ResponseEntity<?> findAll() {
       List<OwnerDTO> owners = ownerService.findAll();
       if(owners.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Não há registros em nosso banco de dados no momento!");
       }
       return ResponseEntity.ok(owners);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        OwnerDTO ownerDTO = ownerService.findById(id);
        if(ownerDTO != null){
            return ResponseEntity.ok(ownerDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há registros de um dono com id: " + id + " em nosso banco de dados");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<OwnerDTO> save(@RequestBody OwnerDTO ownerDTO){
        OwnerDTO owner = ownerService.save(ownerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(owner);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody OwnerDTO ownerDTO){
       OwnerDTO owner = ownerService.update(id, ownerDTO);
       if(owner != null) {
           return ResponseEntity.ok("Dono com o id: " + id + " atualizado com sucesso!!!");
       }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Dono com o id: " + id + " nao encontrado em nossos registros");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(ownerService.findById(id) != null) {
            ownerService.delete(id);
            return ResponseEntity.ok("Dono com o id: " + id + " deletado com sucesso!!!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Dono com o id: " + id + " nao encontrado em nossos registros");
    }
}
