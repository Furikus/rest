package com.dunice.restful.repository;

import com.dunice.restful.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findAllByEmail(String email);
}
