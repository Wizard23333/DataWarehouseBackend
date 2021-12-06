package com.datawarehousebackend.mysql.repository;

import com.datawarehousebackend.mysql.model.DivTimeEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface DivTimeRepository extends JpaRepository<DivTimeEntity, Integer> {

    Optional<DivTimeEntity> findAllByTimeKey(int time_key);

    @Override
    List<DivTimeEntity> findAllById(Iterable<Integer> integers);

    List<DivTimeEntity> findAllByYear(short year);

    // sql根据year查询所有的timeKey
    @Query("select timeKey from DivTimeEntity where year = :year")
    List<Integer> findAllTimeKeyByYear(@Param("year")Short year);

    // sql根据month查询所有的timeKey
    @Query("select timeKey from DivTimeEntity where month = :month")
    List<Integer> findAllTimeKeyByMonth(@Param("month") Byte month);

    @Query("select timeKey from DivTimeEntity where day = :day")
    List<Integer> findAllTimeKeyByDay(@Param("day") Byte day);

    @Query("select timeKey from DivTimeEntity where quarter = :quarter")
    List<Integer> findAllTimeKeyByQuarter(@Param("quarter") Byte quarter);

    @Query("select timeKey from DivTimeEntity where weekday = :weekday")
    List<Integer> findAllTimeKeyByWeekday(@Param("weekday") Byte weekday);



}
