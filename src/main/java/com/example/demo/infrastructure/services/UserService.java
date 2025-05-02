package com.example.demo.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.example.demo.domain.schemas.UserEntity;
import com.example.demo.infrastructure.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity findByEmail(String email) {
        UserEntity probe = new UserEntity();
        probe.setEmail(email);
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnorePaths("id") // Ignora el campo "id"
                .withIgnoreNullValues();
        System.out.println("Probe: " + probe.getEmail());
        Example<UserEntity> example = Example.of(probe,matcher);
        return userRepository.findBy(example, q -> q.first().orElse(null));
    }

    public boolean checkPassword(UserEntity user, String rawPassword) {
        // Aquí puedes usar BCrypt más adelante
        return user.getPassword().equals(rawPassword);
    }
}