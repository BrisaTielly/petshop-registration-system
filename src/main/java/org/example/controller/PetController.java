package org.example.controller;

import org.example.dto.OwnerDTO;
import org.example.dto.PetDTO;
import org.example.services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll() {
        List<PetDTO> pets = petService.findAll();
        if(pets.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há registros em nosso banco de dados no momento!");
        }
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        PetDTO petDTO = petService.findById(id);
        if(petDTO != null){
            return ResponseEntity.ok(petDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há registros de um pet com id: " + id + " em nosso banco de dados");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PetDTO petDTO){
        PetDTO pet = petService.save(petDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Novo pet criado com sucesso: " + pet.getName() + " (ID): " + pet.getId());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PetDTO petDTO){
        PetDTO pet = petService.update(id, petDTO);
        if(pet != null) {
            return ResponseEntity.ok("Pet com o id: " + id + " atualizado com sucesso!!!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Pet com o id: " + id + " nao encontrado em nossos registros");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(petService.findById(id) != null) {
            petService.delete(id);
            return ResponseEntity.ok("Pet com o id: " + id + " deletado com sucesso!!!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Pet com o id: " + id + " nao encontrado em nossos registros");
    }
}
