package com.pfc2.weather.api.repositories;

import com.pfc2.weather.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for users
 * @author jchamorro
 * */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    /**
     * Find user by email
     * @param email User email
     * @return UserEntity Optional
     * */
    Optional<UserEntity> findByEmail(String email);

}
