package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.BridgeActEntity;
import com.datawarehousebackend.mysql.model.BridgeActEntityPK;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BridgeActEntityRepository extends JpaRepository<BridgeActEntity, BridgeActEntityPK> {

    @Query("select asin from BridgeActEntity where actorId = :actorId")
    List<String> findAllAsinByActorId(@Param("actorId") Integer actorId);

    @Query("select actorId from BridgeActEntity where asin = :asin")
    List<Integer> findAllActorIdByAsin(@Param("asin") String asin);
}