package com.aso.codingwiki.repository;


import com.aso.codingwiki.model.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findOptionalByuserEmail(String userEmail);

    UserEntity findByuserEmail(String userEmail);

    Optional<UserEntity> findOpByuserEmail(String userEmail);
}
