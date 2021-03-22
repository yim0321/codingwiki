package com.aso.codingwiki.repository;


import com.aso.codingwiki.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findOptionalByUserEmail(String userEmail);

    UserEntity findByUserEmail(String userEmail);

    Optional<UserEntity> findOpByUserEmail(String userEmail);
}
