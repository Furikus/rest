package com.dunice.restful.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Client {

    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private String email;
    private String phone;

}