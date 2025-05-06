package com.tiagostmg.mercadoapi.repository;

import com.tiagostmg.mercadoapi.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
