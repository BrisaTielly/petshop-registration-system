package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_pet")
public class PetModel {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String name;
     private String species;
     private String breed;
     private int age;

     @ManyToOne
     @JoinColumn(name = "owner_id")
     @JsonIgnore
     OwnerModel owner;

     public PetModel() {
     }

     public PetModel(int age, String breed, Long id, String name, OwnerModel owner, String species) {
          this.age = age;
          this.breed = breed;
          this.id = id;
          this.name = name;
          this.owner = owner;
          this.species = species;
     }

     public int getAge() {
          return age;
     }

     public void setAge(int age) {
          this.age = age;
     }

     public String getBreed() {
          return breed;
     }

     public void setBreed(String breed) {
          this.breed = breed;
     }

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public OwnerModel getOwner() {
          return owner;
     }

     public void setOwner(OwnerModel owner) {
          this.owner = owner;
     }

     public String getSpecies() {
          return species;
     }

     public void setSpecies(String species) {
          this.species = species;
     }
}
