package com.datawarehousebackend.neo4j.entity;



import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;
import java.util.Map;


@Data
@NoArgsConstructor
@RelationshipEntity(type = "cooperate_aanda")
public class Cooperate_aanda implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private Integer num;

    @StartNode
    private Actor startnode;

    @EndNode
    private Actor endnode;

//    public Cooperate_aanda() {
//    }
//
//    public Cooperate_aanda(Actor startNode,Actor endNode,String name,
//                        Integer num){
//        this.actorstart = startNode;
//        this.actorend = endNode;
//        this.name = name;
//        this.num = num;
//    }
//    public Integer getNum() {
//        return num;
//    }
}
