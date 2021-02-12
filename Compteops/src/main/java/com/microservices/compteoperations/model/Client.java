package com.microservices.compteoperations.model;

import lombok.Data;

@Data
public class Client {
    private Long code;
    private String name;
    private String email;
}
