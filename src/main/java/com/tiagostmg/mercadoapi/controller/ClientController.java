package com.tiagostmg.mercadoapi.controller;

import com.tiagostmg.mercadoapi.entity.Client;
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
    public Client save(@RequestBody Client client) {
        return clientService.save(client);
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
    public Client update(@RequestBody Client client) {
        return clientService.save(client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {}

}
