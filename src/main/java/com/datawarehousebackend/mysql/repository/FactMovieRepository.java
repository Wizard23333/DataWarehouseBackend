package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.FactMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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




}
