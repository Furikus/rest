package com.dunice.restful.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    private String name;
    private String email;
    private String phone;
}
