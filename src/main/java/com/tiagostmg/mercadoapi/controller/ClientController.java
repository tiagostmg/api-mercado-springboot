package com.tiagostmg.mercadoapi.controller;

import com.tiagostmg.mercadoapi.dto.ClientDTO;
import com.tiagostmg.mercadoapi.entity.Client;
import com.tiagostmg.mercadoapi.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @Operation(summary = "Cria um novo cliente")
    @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @PostMapping
    public ResponseEntity<List<ClientDTO>> create(@Valid @RequestBody ClientDTO clientDTO) {
        clientService.save(clientDTO.toEntity());
        return ResponseEntity.ok(getAll());
    }

    @Operation(summary = "Lista todos os clientes")
    @GetMapping
    public List<ClientDTO> getAll() {
        return clientService.getAll().stream()
                .map(ClientDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Lista cliente por id")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getById(@PathVariable Long id) {
        Optional<Client> clientOptional = clientService.getById(id);
        return clientOptional.map(client -> ResponseEntity.ok(ClientDTO.fromEntity(client)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um cliente existente")
    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<List<ClientDTO>> update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        Optional<Client> clientOptional = clientService.getById(id);
        if (clientOptional.isPresent()) {
            Client client = clientDTO.toEntity();
            client.setId(id);
            clientService.save(client);
            return ResponseEntity.ok(getAll());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Exclui um cliente")
    @ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<ClientDTO>> delete(@PathVariable Long id) {
        if (clientService.existsById(id)) {
            clientService.delete(id);
            return ResponseEntity.ok(getAll());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
