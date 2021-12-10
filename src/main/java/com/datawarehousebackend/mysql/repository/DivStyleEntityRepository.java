package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.DivStyleEntity;
import com.datawarehousebackend.mysql.model.DivStyleEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivStyleEntityRepository extends JpaRepository<DivStyleEntity, DivStyleEntityPK> {

    DivStyleEntity findByStyleKey(Integer styleKey);
}