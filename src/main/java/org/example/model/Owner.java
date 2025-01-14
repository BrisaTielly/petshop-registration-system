package org.example.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Owner {
    int id;
    String name;
    String phone;
    String email;
}
