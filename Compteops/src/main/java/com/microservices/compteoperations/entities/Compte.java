package com.microservices.compteoperations.entities;

import com.microservices.compteoperations.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private double solde;

    @Temporal(TemporalType.TIMESTAMP)
    Date created_at;

    @Enumerated(EnumType.STRING)
    private TypeCompte type;

    @Enumerated(EnumType.STRING)
    private EtatCompte etat;

    @OneToMany
    Collection<Operation> operations;

    Long clientId;

    @Transient
    Client client;


}


