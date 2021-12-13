package com.datawarehousebackend.neo4j.entity;



import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@RelationshipEntity(type = "Cooperate_aanda")
public class Cooperate_aanda implements Serializable {
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
    private Actor actorend;

//    public Integer getNum() {
//        return num;
//    }
}
