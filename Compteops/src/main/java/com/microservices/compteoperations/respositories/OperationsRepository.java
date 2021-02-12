package com.microservices.compteoperations.respositories;

import com.microservices.compteoperations.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationsRepository extends JpaRepository<Operation, Long> {
}
