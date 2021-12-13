package com.datawarehousebackend.neo4j.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RelationshipEntity(type = "Cooperate_dandd")
public class Cooperate_dandd implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private Integer num;

    @StartNode
    private Director directorstart;

    @EndNode
    private Director directorend;
}
