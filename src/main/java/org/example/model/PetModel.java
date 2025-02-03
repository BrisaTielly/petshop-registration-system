package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
