package com.datawarehousebackend.neo4j.dao;

import com.datawarehousebackend.neo4j.entity.Director;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DirectorRepository extends Neo4jRepository<Director,Long> {
}
