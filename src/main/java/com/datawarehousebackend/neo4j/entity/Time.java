package com.datawarehousebackend.neo4j.entity;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;



@Data
@Builder
@NodeEntity(label = "Time")
public class Time {
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String name;
}

