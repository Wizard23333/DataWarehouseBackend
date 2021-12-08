package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.DivActorEntity;
import com.datawarehousebackend.mysql.model.DivActorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DivActorRepository extends JpaRepository<DivActorEntity, DivActorEntityPK> {

    @Query("select movieName from DivActorEntity where actorName = :actorName")
    List<String> findAllMovieNameByActorName(@Param("actorName") String actorName);

    @Query("select actorName from DivActorEntity where actorName like %:actorName%")
    List<String> findAllFuzzyActorNameByActorName(@Param("actorName") String actorName);
}