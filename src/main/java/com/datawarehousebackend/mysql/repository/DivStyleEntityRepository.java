package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.DivStyleEntity;
import com.datawarehousebackend.mysql.model.DivStyleEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DivStyleEntityRepository extends JpaRepository<DivStyleEntity, DivStyleEntityPK> {

    DivStyleEntity findByStyleKey(Integer styleKey);

    DivStyleEntity findByStyleName(String styleName);

    List<DivStyleEntity> findTop100ByOrderByStyleNumDesc();

}