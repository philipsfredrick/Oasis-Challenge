package com.oasis.taskmanagementapp.repository;

import com.oasis.taskmanagementapp.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);

    @Modifying
    @Query(value = "UPDATE token SET expired = true, revoked = true WHERE user_id = :userId", nativeQuery = true)
    void invalidateAllUserTokens(@Param("userId") Long userId);
}
