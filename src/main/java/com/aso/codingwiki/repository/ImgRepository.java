package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.board.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImgRepository extends JpaRepository<ImgEntity,Long> {
    Optional<ImgEntity> findByUuid(String uuid);
}
