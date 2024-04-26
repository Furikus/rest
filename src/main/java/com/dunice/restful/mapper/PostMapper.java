package com.dunice.restful.mapper;

import com.dunice.restful.dto.ClientDto;
import com.dunice.restful.dto.PostDto;
import com.dunice.restful.model.Client;
import com.dunice.restful.model.Post;
import com.dunice.restful.model.Tags;
import com.dunice.restful.repository.ClientRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {

   PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

   @Mapping(target = "clientId", source = "client", qualifiedByName = "mapToClientId")
    PostDto toDto(Post post);
    @Named("mapToClientId")
    default Integer mapToClientId(Client client) {
        return client.getId();
    }

    //@Mapping(target = "id", source = "clientId", qualifiedByName = "getIdDTO")
    Post toEntity(PostDto PostDto);

    @Named("getIdDTO")
    default Integer mapToClient(ClientDto clientDto) {
        return clientDto.getId();
    }



    Client map(Integer value);
    Tags map(String value);
    String map(Tags value);
}
