package com.example.demo.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.schemas.PublicEntity;

public interface PublicEntityRepository extends JpaRepository<PublicEntity, Long> {
    // Custom query methods can be defined here if needed
    // For example:
    // List<PublicEntity> findBySomeField(String someField);
    List<PublicEntity> findByUserOwned_Id(Long userId); // <-- AQUÍ tú lo creas
    
}
