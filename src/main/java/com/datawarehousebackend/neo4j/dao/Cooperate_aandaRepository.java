package com.datawarehousebackend.neo4j.dao;

import com.datawarehousebackend.neo4j.entity.Actor;
import com.datawarehousebackend.neo4j.entity.Cooperate_aanda;
import com.datawarehousebackend.neo4j.entity.test;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public interface Cooperate_aandaRepository extends Neo4jRepository<Cooperate_aanda,Long> {


//    @Query("MATCH p=(a)-[r:cooperate_aanda]->(b) RETURN p ORDER BY r.num DESC")
    @Query("MATCH p=(a)-[r:cooperate_aanda]->(b) RETURN r.num,a.name,b.name ORDER BY r.num DESC")
    List<Map<String,Object>> findTopByName(@Param("name") String name);
//    @Query("MATCH p=(a)-[r:cooperate_aanda]->(b) RETURN p ORDER BY r.num DESC")
//
//    Iterable<Cooperate_aanda> findTopByName(@Param("name") String name);
//    @Query("MATCH (a)-[r:cooperate_aanda]->(b) RETURN r ORDER BY r.num DESC")
//    Iterable<Cooperate_aanda> findByname(@Param("name") String name);
//    List<Map<String,Object>>  gettop();
//        @Query(value = "match (n:Actor) where(n.name= {name}) return n.name")

//    @Query("MATCH (a)-[r:Cooperate_aanda]->(b) RETURN r ")
//    List<Cooperate_aanda> findTopByName(@Param("name") String name);
//    @Query("MATCH p=()-[r:cooperate_aanda]->() RETURN p")
//
//    Iterable<Cooperate_aanda> findTopByName(@Param("name") String name);
//    @Query(value = "match (n:Actor) where(n.name= {name}) return n")
//    List<Cooperate_aanda> findTopByName(@Param("name") String name);
}
