package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.DivActorEntity;
import com.datawarehousebackend.mysql.model.DivActorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DivActorRepository extends JpaRepository<DivActorEntity, DivActorEntityPK> {

    @Query(value = "select movie_name from div_actor where actor_name = :actorName", nativeQuery = true)
    List<Map<String, Object>> findAllMovieNameByActorName(@Param("actorName") String actorName);

    @Query(value = "select actor_name from div_actor where actor_name like %:actorName%", nativeQuery = true)
    List<Map<String, Object>> findAllFuzzyActorNameByActorName(@Param("actorName") String actorName);
}