package com.microservices.client.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long code;

    @NotNull
    private String name;

    @Column(unique = true)
    private String email;
}
