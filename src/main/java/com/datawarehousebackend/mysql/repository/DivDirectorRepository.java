package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.DivDirectorEntity;
import com.datawarehousebackend.mysql.model.DivDirectorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DivDirectorRepository extends JpaRepository<DivDirectorEntity, DivDirectorEntityPK> {

    @Query(value = "select movie_name from div_director where director_name = :directorName",nativeQuery = true)
    List<Map<String, Object>>  findAllMovieNameByDirectorName(@Param("directorName") String directorName);

    // 使用原生sql查询
    @Query(value = "select director_id, director_name from div_director where director_name like %:directorName%", nativeQuery = true)
    List<Map<String, Object>> findAllFuzzyDirectorNameByDirectorName(@Param("directorName") String directorName);

}
