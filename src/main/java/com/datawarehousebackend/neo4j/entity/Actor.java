package com.datawarehousebackend.neo4j.entity;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


import java.io.Serializable;
@Data
@Builder
@NodeEntity(label = "Actor")
public class Actor implements Serializable {


    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String name;

}
