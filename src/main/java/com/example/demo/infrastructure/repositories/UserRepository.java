package com.example.demo.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.schemas.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
