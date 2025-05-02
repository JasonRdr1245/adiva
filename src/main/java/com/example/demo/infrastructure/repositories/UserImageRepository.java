package com.example.demo.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.schemas.UserImageEntity;

import jakarta.transaction.Transactional;

public interface UserImageRepository extends JpaRepository<UserImageEntity, Long> {
    Optional<UserImageEntity> findByUser_Id(Long userId);

    // delte by user id
    @Modifying
    @Transactional
    @Query("DELETE FROM UserImageEntity u WHERE u.user.id = :userId")
    void deleteByUserId(@Param("userId") int userId);

}
