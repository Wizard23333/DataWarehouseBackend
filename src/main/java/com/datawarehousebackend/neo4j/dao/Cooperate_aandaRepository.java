package com.datawarehousebackend.neo4j.dao;

import com.datawarehousebackend.neo4j.entity.Cooperate_aanda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public interface Cooperate_aandaRepository extends Neo4jRepository<Cooperate_aanda,Long> {

//    @Query("MATCH (a)-[r:cooperate_aanda]->(b) RETURN r.num,a.name,b.name ORDER BY r.num DESC")
//    List<Map<String,Object>>  gettop();
//        @Query(value = "match (n:Actor) where(n.name= {name}) return n.name")
//    List<Map<String,Object>> findByname(@Param("name") String name);
}
