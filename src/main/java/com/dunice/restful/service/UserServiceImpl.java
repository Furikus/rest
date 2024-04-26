package com.dunice.restful.service;

import com.dunice.restful.model.User;
import com.dunice.restful.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void create(User user) {
        User user1 = User.builder()
                .name(user.getName())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
        userRepository.save(user1);
    }
}