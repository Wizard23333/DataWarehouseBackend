package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.DivMovieSeriesEntity;
import com.datawarehousebackend.mysql.model.DivMovieSeriesEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivMovieSeriesEntityRepository extends JpaRepository<DivMovieSeriesEntity, DivMovieSeriesEntityPK> {
    DivMovieSeriesEntity findByMovieSeriesId(Integer movieSeriesId);
}