package com.datawarehousebackend.neo4j.dao;

import com.datawarehousebackend.neo4j.entity.Cooperate_aanda;
import com.datawarehousebackend.neo4j.entity.test;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface testRepository extends Neo4jRepository<test,Long> {

    @Query("match(a),(b) where a.name='Alvin Alexis' and b.name = 'Farah White'"
            + " create p = (a)-[r:test{name:'fsd',num:5}]->(b) return r ")
    Iterable<test> createLikes(String startNodeID,String endNodeID, String name,Integer num);

    @Query("MATCH p=()-[r:test]->() RETURN p")

    Iterable<test> findTopByName(@Param("name") String name);
}
