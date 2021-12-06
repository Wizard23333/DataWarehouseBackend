package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.FactMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FactMovieRepository extends JpaRepository<FactMovieEntity, String>, JpaSpecificationExecutor<FactMovieEntity> {
    @Override
    List<FactMovieEntity> findAllById(Iterable<String> strings);
    // 通过asin查找
    FactMovieEntity findAllByAsin(String asin);
    // 通过timeKey列表查找
    List<FactMovieEntity> findAllByTimeKey(Integer timeKey);




}
