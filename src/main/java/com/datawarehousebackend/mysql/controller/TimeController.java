package com.datawarehousebackend.mysql.controller;

import com.datawarehousebackend.mysql.model.DivTimeEntity;
import com.datawarehousebackend.mysql.model.FactMovieEntity;
import com.datawarehousebackend.mysql.repository.DivTimeRepository;
import com.datawarehousebackend.mysql.repository.FactMovieRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

// 采用相关时间参数来访问的接口
@RestController
@RequestMapping("api/time")
@Api(tags = "时间参数接口")
public class TimeController {
    @Resource
    DivTimeRepository divTimeRepository;
    @Resource
    FactMovieRepository factMovieRepository;


    @Operation(summary = "测试接口")
    @GetMapping("test")
    public String helloTest() {
        return "Hello World!";
    }

    @GetMapping("get_a_time")
    public List<DivTimeEntity> getATime() {
        List<Integer> listInt = new ArrayList<Integer>();
        System.out.println("^^");
        List<DivTimeEntity> divTimeEntity = divTimeRepository.findAllById(listInt);
        return divTimeEntity;
    }

    @Operation(summary = "通过year来查询电影信息")
    @GetMapping("year/{year}")
    public List<FactMovieEntity> getByYear(@PathVariable Short year) {
        return findAllMoviesByTimeKeyList(divTimeRepository.findAllTimeKeyByYear(year));
    }

    @Operation(summary = "通过month来查询电影")
    @GetMapping("month/{month}")
    public List<FactMovieEntity> getByMonth(@PathVariable Byte month) {
        return findAllMoviesByTimeKeyList(divTimeRepository.findAllTimeKeyByMonth(month));
    }


    @Operation(summary = "通过day来查询电影")
    @GetMapping("day/{day}")
    public List<FactMovieEntity> getByDay(@PathVariable Byte day) {
        return findAllMoviesByTimeKeyList(divTimeRepository.findAllTimeKeyByDay(day));
    }

    @Operation(summary = "通过quarter来查询电影")
    @GetMapping("quarter/{quarter}")
    public List<FactMovieEntity> getByQuarter(@PathVariable Byte quarter) {
        return findAllMoviesByTimeKeyList(divTimeRepository.findAllTimeKeyByQuarter(quarter));
    }

    @Operation(summary = "通过weekday来查询电影")
    @GetMapping("weekday/{weekday}")
    public List<FactMovieEntity> getByWeekDay(@PathVariable Byte weekday) {
        return findAllMoviesByTimeKeyList(divTimeRepository.findAllTimeKeyByWeekday(weekday));
    }

    @Operation(summary = "通过year和month来查询电影")
    @GetMapping("year-and-month")
    public List<FactMovieEntity> getByYearAndMonth(@Param("year") Short year, @Param("month") Byte month) {
        return findAllMoviesByTimeKeyList(divTimeRepository.findAllTimeKeyByYearAndMonth(year, month));
    }

    @Operation(summary = "通过year和day来查询电影")
    @GetMapping("year-and-day")
    public List<FactMovieEntity> getByYearAndDay(@Param("year") Short year, @Param("day") Byte day) {
        return findAllMoviesByTimeKeyList(divTimeRepository.findAllTimeKeyByYearAndDay(year, day));
    }

    @Operation(summary = "通过month和day来查询电影")
    @GetMapping("month-and-day")
    public List<FactMovieEntity> getByMonthAndDay(@Param("month") Byte month, @Param("day") Byte day) {
        return findAllMoviesByTimeKeyList(divTimeRepository.findAllTimeKeyByMonthAndDay(month, day));
    }

    @Operation(summary = "通过year和month和day来查询电影")
    @GetMapping("year-and-month-day")
    public List<FactMovieEntity> getByYearAndMonthAndDay(@Param("year") Short year, @Param("month") Byte month, @Param("day") Byte day) {
        return findAllMoviesByTimeKeyList(divTimeRepository.findAllTimeKeyByYearAndMonthAndDay(year, month, day));
    }

    @Operation(summary = "通过year和quarter来查询电影")
    @GetMapping("year-and-quarter")
    public List<FactMovieEntity> getByYearAndQuarter(@Param("year") Short year, @Param("quarter") Byte quarter) {
        return findAllMoviesByTimeKeyList(divTimeRepository.findAllTimeKeyByYearAndQuarter(year, quarter));
    }

    // 根据timeKeyList获取电影列表
    private List<FactMovieEntity> findAllMoviesByTimeKeyList(List<Integer> timeKeyList) {
        List<FactMovieEntity> factMovieEntityList = new ArrayList<>();
        for (Integer timeKey : timeKeyList) {
            factMovieEntityList.addAll(factMovieRepository.findAllByTimeKey(timeKey));
        }
        return factMovieEntityList;
    }

}
