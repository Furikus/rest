package com.dunice.restful.repository;

import com.dunice.restful.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findAllByEmail(String email);
    @Query(value = "SELECT c from Client c")
    List<Client> findAll();
    @Query("select c from Client c where c.id = :id ")
    Optional<Client> findById(Integer id);
}
