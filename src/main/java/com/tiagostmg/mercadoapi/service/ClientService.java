package com.tiagostmg.mercadoapi.service;

import com.tiagostmg.mercadoapi.entity.Client;
import com.tiagostmg.mercadoapi.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public void save(Client client) {
        clientRepository.save(client);
    }

    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }
}
