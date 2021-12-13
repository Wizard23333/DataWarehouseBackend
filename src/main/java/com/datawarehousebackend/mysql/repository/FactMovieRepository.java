package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.controller.MovieController;
import com.datawarehousebackend.mysql.model.FactMovieEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface FactMovieRepository extends JpaRepository<FactMovieEntity, String>, JpaSpecificationExecutor<FactMovieEntity> {
    @Override
    List<FactMovieEntity> findAllById(Iterable<String> strings);

    // 通过asin查找
    FactMovieEntity findByAsin(String asin);

    List<FactMovieEntity> findByStyleKey(Integer styleKey);

    // 通过timeKey列表查找
    List<FactMovieEntity> findAllByTimeKey(Integer timeKey);

    // 通过电影名称来查找 精确匹配
    List<FactMovieEntity> findAllByTitle(String title);

    // 通过电影名模糊查询
    List<FactMovieEntity> findAllByTitleContaining(String title);

    @Query("select count(asin) from FactMovieEntity where imdbScore >= :score")
    Integer findMovieNumByMoreThanScore(@Param("score") Double score);

    @Query(nativeQuery = true, value = "select count(asin) from fact_movie where (select sum(helpful_num) from div_review where asin = fact_movie.asin) >= :helpfulNum")
    Integer findMovieNumByHelpfulNum(@Param("helpfulNum") Integer helpfulNum);

    @Query("select asin from FactMovieEntity where timeKey = :timeKey and styleKey = :styleKey")
    List<String> findAsinByTimeKeyAndStyleKey(@Param("timeKey") Integer timeKey, @Param("styleKey") Integer styleKey);

    @Query("select avg(imdbScore) as avgImdb, avg(reviewPoint) as avgReviewPoint from FactMovieEntity where styleKey = :styleKey")
    Map<String, Object> findAvgImdbScoreAndReviewPoint(@Param("styleKey") Integer styleKey);



}
