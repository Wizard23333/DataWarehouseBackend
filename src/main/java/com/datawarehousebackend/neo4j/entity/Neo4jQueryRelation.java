package com.datawarehousebackend.neo4j.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 关系
 * @author YangBM
 */
@Data
public class Neo4jQueryRelation implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 开始节点id
     */
    private Long start;
    /**
     * 结束节点id
     */
    private Long end;
    /**
     * 关系类型
     */
    private String type;
    /**
     * id
     */
    private Long id;

    /**
     * 标签属性
     */
    private Map<String, Object> property;
}
