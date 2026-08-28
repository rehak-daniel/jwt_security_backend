package com.securityproject.applicationuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.securityproject.applicationuser.model.domain.ApplicationUserEntity;
import java.util.UUID;
import java.util.Optional;


public interface UserRepository extends JpaRepository<ApplicationUserEntity, UUID> {
    
    Optional<ApplicationUserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

}
