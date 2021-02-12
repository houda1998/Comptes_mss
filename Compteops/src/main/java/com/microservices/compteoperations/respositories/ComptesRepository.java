package com.microservices.compteoperations.respositories;

import com.microservices.compteoperations.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComptesRepository extends JpaRepository<Compte, Long> {
}
