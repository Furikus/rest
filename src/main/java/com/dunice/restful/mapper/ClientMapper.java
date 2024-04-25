package com.dunice.restful.mapper;

import com.dunice.restful.dto.ClientDto;
import com.dunice.restful.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);
}
