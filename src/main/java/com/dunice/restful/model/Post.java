package com.dunice.restful.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    private Integer id;
    private String title;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne
    private Client client;




}
