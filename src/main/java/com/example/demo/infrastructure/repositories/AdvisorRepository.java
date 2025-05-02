package com.example.demo.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.schemas.AdvisorEntity;

public interface AdvisorRepository extends JpaRepository<AdvisorEntity, Long> {
}
