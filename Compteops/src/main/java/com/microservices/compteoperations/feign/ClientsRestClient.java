package com.microservices.compteoperations.feign;

import com.microservices.compteoperations.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="CLIENT-SERVICE")
public interface ClientsRestClient {
    @GetMapping("/clients/{id}")
    Client findClientById(@PathVariable("id") Long clientId);
    @GetMapping(path="/clients")
    PagedModel<Client> pageClient(@RequestParam(name = "page")int page, @RequestParam(name = "size")int size);

}
