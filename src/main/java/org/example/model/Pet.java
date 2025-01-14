package org.example.model;

import lombok.*;

@Builder
@Value
public class Pet {
     int id;
     String name;
     String species;
     String breed;
     int age;
     Owner owner;
}
