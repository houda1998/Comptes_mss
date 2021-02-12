package com.microservices.compteoperations.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;

    private double montant;

    @Temporal(TemporalType.TIMESTAMP)
    Date created_at;

    @Enumerated(EnumType.STRING)
    private TypeOperation type;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Compte compte;


}
