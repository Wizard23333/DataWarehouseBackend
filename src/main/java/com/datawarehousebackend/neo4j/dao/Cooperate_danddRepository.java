package com.datawarehousebackend.neo4j.dao;

import com.datawarehousebackend.neo4j.entity.Cooperate_dandd;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface Cooperate_danddRepository extends Neo4jRepository<Cooperate_dandd,Long> {
    @Query("MATCH p=(a)-[r:cooperate_dandd]->(b) RETURN r.num,a.name,b.name ORDER BY r.num DESC")
    List<Map<String,Object>> findTopByName(@Param("name") String name);
}
