package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.BridgeDirectEntity;
import com.datawarehousebackend.mysql.model.BridgeDirectEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BridgeDirectEntityRepository extends JpaRepository<BridgeDirectEntity, BridgeDirectEntityPK>, JpaSpecificationExecutor<BridgeDirectEntity> {

    // 通过id查找演过的电影asin
    @Query("select asin from BridgeDirectEntity where directorId = :directorId")
    List<String> findAllAsinByDirectorId(@Param("directorId") Integer directorId);

    // 通过asin来查找directorId，即一起合作过的
    @Query("select directorId from BridgeDirectEntity where asin = :asin")
    List<Integer> findAllDirectorIdByAsin(@Param("asin") String asin);

}