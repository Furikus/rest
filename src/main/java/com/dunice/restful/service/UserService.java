package com.dunice.restful.service;

import com.dunice.restful.dto.ClientDto;
import com.dunice.restful.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    /**
     * Возвращает список всех имеющихся клиентов
     * @return список клиентов
     */
    List<User> readAll();

}
