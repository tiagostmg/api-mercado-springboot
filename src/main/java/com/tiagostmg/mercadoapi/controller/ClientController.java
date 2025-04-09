package com.tiagostmg.mercadoapi.controller;

import com.tiagostmg.mercadoapi.model.Client;
import com.tiagostmg.mercadoapi.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public List<Client> save(@RequestBody Client client) {
        clientService.save(client);
        return getAll();
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Client> getById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @PutMapping
    public List<Client> update(@RequestBody Client client) {
        clientService.save(client);
        return getAll();
    }

    @DeleteMapping("/{id}")
    public List<Client> delete(@PathVariable Long id) {
        clientService.delete(id);
        return getAll();
    }

}
