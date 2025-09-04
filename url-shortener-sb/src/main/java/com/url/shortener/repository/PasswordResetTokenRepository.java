package com.url.shortener.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url.shortener.models.PasswordResetToken;
import com.url.shortener.models.User;

import jakarta.transaction.Transactional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    @Transactional
    void deleteByUser(User user);

    Optional<PasswordResetToken> findByToken(String token);

    boolean existsByUser(User user);
}
