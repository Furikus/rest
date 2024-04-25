package com.dunice.restful.service;

import com.dunice.restful.dto.ClientDto;
import com.dunice.restful.mapper.ClientMapper;
import com.dunice.restful.model.Client;

import com.dunice.restful.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    @Override
    public void create(ClientDto clientdto) {
        Client client = clientMapper.toEntity(clientdto);
//        Client client = Client.builder()
//                .name(clientdto.getName())
//                .email(clientdto.getEmail())
//                .phone(clientdto.getPhone())
//                .build();
        clientRepository.save(client);
    }

    @Override
    public List<ClientDto> readAll() {
        return clientRepository.findAll().stream()
                .map(client -> clientMapper.toDto(client)).collect(Collectors.toList());
//                ClientDto.builder()
//                .name(client.getName())
//                .email(client.getEmail())
//                .phone(client.getPhone())
//                .build())
//                .toList();

    }

    @Override
    public ClientDto read(int id) {
        var client = clientRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("client not found"));
        return  clientMapper.toDto(client);
        //        return ClientDto.builder()
//                .name(client.getName())
//                .email(client.getEmail())
//                .phone(client.getPhone())
//                .build();
//        var OptClient = clientRepository.findById(id);
//        if (OptClient.isPresent()) {
//            var client = OptClient.get();
//
//        }
//        return ClientDto.builder().build();
    }

    @Override
    public boolean update(ClientDto clientdto, int id) {
        var client = clientRepository.findById(id).orElse(Client.builder().build());
        client.setName(clientdto.getName());
        client.setEmail(clientdto.getEmail());
        client.setPhone(clientdto.getPhone());
        clientRepository.save(client);
        return true;
//        if (optClient.isPresent()) {
//            var client = optClient.get();
//            client.setName(clientdto.getName());
//            client.setEmail(clientdto.getEmail());
//            client.setPhone(clientdto.getPhone());
//            clientRepository.save(client);
//            return true;
//        }
//        return false;
    }

    @Override
    public boolean delete(int id) {
        var client = clientRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("client not found"));;
            clientRepository.delete(client);
        return true;
    }
}