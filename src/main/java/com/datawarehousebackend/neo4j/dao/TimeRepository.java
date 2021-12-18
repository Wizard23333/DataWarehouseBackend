package com.datawarehousebackend.neo4j.dao;


import com.datawarehousebackend.neo4j.entity.Time;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TimeRepository extends Neo4jRepository<Time,Long> {

    @Query("MATCH p=(a)-[r:has_time]->(b) RETURN a.name,a.IMDBgrade,a.ReviewPoint,b.name[2],b.name[0],b.name[1]")
    List<Map<String,Object>> findTopByyear(@Param("name") String name);

    @Query(value = "MATCH p=(a)-[r:do_dir]->(b) where (a.name= $name) RETURN b.name ")
    List<String> findTopByyear1(@Param("name") String name);

    @Query(value = "MATCH p=(a)-[r:do_act]->(b) where (a.name= $name) RETURN b.name ")
    List<String> findTopByyear2(@Param("name") String name);

    @Query(value = "MATCH p=(a)-[r:belongs_to]->(b) where (a.name= $name) RETURN b.name ")
    List<String> findTopByyear3(@Param("name") String name);

    @Query(value = "MATCH p=(a)-[r:has_version]->(b) where (a.name= $name) RETURN b.name ")
    List<String> findTopByyear4(@Param("name") String name);
}
