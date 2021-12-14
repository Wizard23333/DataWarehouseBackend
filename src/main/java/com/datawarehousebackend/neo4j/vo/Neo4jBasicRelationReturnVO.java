package com.datawarehousebackend.neo4j.vo;

import com.datawarehousebackend.neo4j.entity.Actor;
import com.datawarehousebackend.neo4j.entity.Cooperate_aanda;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础返回关系VO
 * 关系
 * @author YangBM
 */
@Data
public class Neo4jBasicRelationReturnVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Actor start;
    private Cooperate_aanda relationship;
    private Actor end;
}
