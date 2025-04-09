package com.tiagostmg.mercadoapi.repository;

import com.tiagostmg.mercadoapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
