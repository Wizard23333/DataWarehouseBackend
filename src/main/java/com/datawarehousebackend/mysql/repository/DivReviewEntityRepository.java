package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.DivReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DivReviewEntityRepository extends JpaRepository<DivReviewEntity, String> {
    List<DivReviewEntity> findAllByAsin(String asin);
}