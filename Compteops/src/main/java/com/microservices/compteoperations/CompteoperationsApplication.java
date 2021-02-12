package com.microservices.compteoperations;

import com.microservices.compteoperations.entities.*;
import com.microservices.compteoperations.feign.ClientsRestClient;
import com.microservices.compteoperations.respositories.ComptesRepository;
import com.microservices.compteoperations.respositories.OperationsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class CompteoperationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteoperationsApplication.class, args);
    }
    @Bean
    CommandLineRunner start(
            ComptesRepository compteRepository,
            OperationsRepository operationRepository,
            ClientsRestClient customerRestClient
    ) {
        return args -> {

            customerRestClient.pageClient(0, 20).forEach(client -> {
                Compte compte = new Compte();
                compte.setSolde(2000.);
                compte.setEtat(EtatCompte.ACTIVE);
                compte.setType(TypeCompte.COURANT);
                compte.setCreated_at(new Date());
                compte.setClientId(client.getCode());
                compteRepository.save(compte);
                System.out.println(client.getCode());
            });
            compteRepository.findAll().forEach(compte -> {
                Operation operation = new Operation();
                operation.setCreated_at(new Date());
                operation.setType(TypeOperation.DEBIT);
                operation.setCompte(compte);
                operation.setMontant(200.);
                compte.setSolde(compte.getSolde() - 200);
                compteRepository.save(compte);
                operationRepository.save(operation);
                System.out.println(compte.getCode());
            });
        };
    }
}
