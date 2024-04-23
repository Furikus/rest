package com.dunice.restful.controller;

import com.dunice.restful.dto.ClientDto;
import com.dunice.restful.model.Client;
import com.dunice.restful.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class Controller {

    private final ClientService clientService;

    @PostMapping(value = "/clients")
    public ResponseEntity<?> create(@RequestBody @Valid ClientDto client) {
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<List<ClientDto>> read() {
        final List<ClientDto> clients = clientService.readAll();

        return clients != null &&  !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<?> read(@PathVariable @Min(0) int id) {
        final ClientDto clientdto = clientService.read(id);

        return clientdto != null
                ? new ResponseEntity<>(clientdto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/clients/{id}")

    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody @Valid ClientDto client) {
        final boolean updated = clientService.update(client, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}