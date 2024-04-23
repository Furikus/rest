package com.dunice.restful.dto;

import com.dunice.restful.model.Client;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class PostDto {
    @NotBlank(message = "Field name must be non empty")
    private String title;
    private Integer clientId;
}
