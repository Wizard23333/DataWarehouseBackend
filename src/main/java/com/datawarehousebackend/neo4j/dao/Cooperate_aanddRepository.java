package com.datawarehousebackend.neo4j.dao;

import com.datawarehousebackend.neo4j.entity.Cooperate_aandd;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface Cooperate_aanddRepository extends Neo4jRepository<Cooperate_aandd,Long> {
    @Query("MATCH p=(a)-[r:cooperate_aandd]->(b) RETURN r.num,a.name,b.name ORDER BY r.num DESC")
    List<Map<String,Object>> findTopByName(@Param("name") String name);
}
