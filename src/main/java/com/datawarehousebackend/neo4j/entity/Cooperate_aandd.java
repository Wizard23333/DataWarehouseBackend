package com.datawarehousebackend.neo4j.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RelationshipEntity(type = "cooperate_aandd")
public class Cooperate_aandd implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private Integer num;

    @StartNode
    private Actor actorstart;

    @EndNode
    private Director directorend;
}
