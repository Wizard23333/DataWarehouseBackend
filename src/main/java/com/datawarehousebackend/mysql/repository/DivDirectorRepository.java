package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.DivDirectorEntity;
import com.datawarehousebackend.mysql.model.DivDirectorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DivDirectorRepository extends JpaRepository<DivDirectorEntity, DivDirectorEntityPK> {

    @Query("select movieName from DivDirectorEntity where directorName = :directorName")
    List<String> findAllMovieNameByDirectorName(@Param("directorName") String directorName);

    @Query("select directorId, directorName from DivDirectorEntity where directorName like %:directorName%")
    List<Object> findAllFuzzyDirectorNameByDirectorName(@Param("directorName") String directorName);

}
