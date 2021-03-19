package com.aso.codingwiki.repository;

import com.aso.codingwiki.model.statpoint.StarPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarPointRepository extends JpaRepository<StarPointEntity,Long> {
}
