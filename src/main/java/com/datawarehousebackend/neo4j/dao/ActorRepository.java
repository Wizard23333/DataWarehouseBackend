package com.datawarehousebackend.neo4j.dao;

import com.datawarehousebackend.neo4j.entity.Actor;
import com.datawarehousebackend.neo4j.entity.Cooperate_aanda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface ActorRepository extends Neo4jRepository<Actor,Long> {

    @Query(value = "match (n:Actor) where(n.name= {name}) return n")
    List<Actor> findByname(@Param("name") String name);

//    @Query("MATCH (a)-[r:cooperate_aanda]->(b) RETURN r.name ")
//    List<String> findTopByName(@Param("name") String name);

}
