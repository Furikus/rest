package com.dunice.restful.controller;

import com.dunice.restful.dto.ClientDto;
import com.dunice.restful.model.User;
import com.dunice.restful.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test/")
@AllArgsConstructor
public class TestController {

    @GetMapping("/welcome")
    public String welcome(){
        return "This is unprotected page";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String pageForUser(){
        return "This is page for only users";
    }


    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdmins(){
        return "This is page for only admins";
    }


    @GetMapping("/all")
    public String pageForAll(){
        return "This is page for all employees";
    }

}