package com.microservices.compteoperations.services;

import com.microservices.compteoperations.entities.Compte;
import com.microservices.compteoperations.entities.EtatCompte;
import com.microservices.compteoperations.entities.Operation;
import com.microservices.compteoperations.entities.TypeCompte;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface CompteOpsService {
    public Compte addCompte(Compte compte);
    public Compte verseMontantCompte(Long compteId, double montant);
    public Compte retraitMontantCompte(Long compteId, double montant);
    public double virementCompte(Long compteDmetteurId,Long compteDestinataireId, double montant);
    public Collection<Operation> listOperations(Long compteId);
    public Compte getCompteClient(Long compteId);
    public Compte editCompteEtat(Long compteId, boolean actif);

}
