package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.FactMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface FactMovieRepository extends JpaRepository<FactMovieEntity, String>, JpaSpecificationExecutor<FactMovieEntity> {
    @Override
    List<FactMovieEntity> findAllById(Iterable<String> strings);
    // 通过asin查找
    FactMovieEntity findAllByAsin(String asin);
    // 通过timeKey列表查找
    List<FactMovieEntity> findAllByTimeKey(Integer timeKey);
    // 通过电影名称来查找 精确匹配
    List<FactMovieEntity> findAllByTitle(String title);
    // 通过电影名模糊查询
    List<FactMovieEntity> findAllByTitleContaining(String title);

    @Query("select count(asin) from FactMovieEntity where imdbScore >= :score")
    Integer findMovieNumByMoreThanScore(@Param("score") Double score);




}
