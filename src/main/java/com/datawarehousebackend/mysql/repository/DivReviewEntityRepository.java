package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.DivReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DivReviewEntityRepository extends JpaRepository<DivReviewEntity, String> {
    List<DivReviewEntity> findAllByAsin(String asin);

    @Query("select avg(sentiment) from DivReviewEntity where asin = :asin")
    Double findAvgSentimentByAsin(@Param("asin") String asin);
}