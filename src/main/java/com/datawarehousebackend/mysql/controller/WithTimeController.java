package com.datawarehousebackend.mysql.controller;

import com.datawarehousebackend.mysql.model.DivTimeEntity;
import com.datawarehousebackend.mysql.model.FactMovieEntity;
import com.datawarehousebackend.mysql.repository.DivTimeRepository;
import com.datawarehousebackend.mysql.repository.FactMovieRepository;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.GeneratorType;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.security.auth.callback.TextInputCallback;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 采用相关时间参数来访问的接口
@RestController
@RequestMapping("api/time")
@Api(tags = "时间参数接口")
public class WithTimeController {

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
        // 根据年来找到timekey列表
        List<Integer> timeKeyList = divTimeRepository.findAllTimeKeyByYear(year);
        System.out.println(timeKeyList);
        List<FactMovieEntity> factMovieEntityList = new ArrayList<>();
        for (Integer timeKey : timeKeyList) {
            // 对于每一个timekey，查找到对应的年
            factMovieEntityList.addAll(factMovieRepository.findAllByTimeKey(timeKey));
        }
        return factMovieEntityList;
    }

    @Operation(summary = "通过month来查询电影")
    @GetMapping("month/{month}")
    public List<FactMovieEntity> getByMonth(@PathVariable Byte month) {
        List<Integer> timeKeyList = divTimeRepository.findAllTimeKeyByMonth(month);
        List<FactMovieEntity> factMovieEntityList = new ArrayList<>();
        for (Integer timeKey: timeKeyList) {
            factMovieEntityList.addAll(factMovieRepository.findAllByTimeKey(timeKey));
        }
        return factMovieEntityList;
    }

    @Operation(summary = "通过quarter来查询电影")
    @GetMapping("quarter/{quarter}")
    public List<FactMovieEntity> getByQuarter(@PathVariable Byte quarter) {
        List<Integer> timeKeyList = divTimeRepository.findAllTimeKeyByQuarter(quarter);
        List<FactMovieEntity> factMovieEntityList = new ArrayList<>();
        for (Integer timeKey: timeKeyList) {
            factMovieEntityList.addAll(factMovieRepository.findAllByTimeKey(timeKey));
        }
        return factMovieEntityList;
    }


    @Operation(summary = "通过day来查询电影")
    @GetMapping("day/{day}")
    public List<FactMovieEntity> getByDay(@PathVariable Byte day) {
        List<Integer> timeKeyList = divTimeRepository.findAllTimeKeyByDay(day);
        List<FactMovieEntity> factMovieEntityList = new ArrayList<>();
        for (Integer timeKey: timeKeyList) {
            factMovieEntityList.addAll(factMovieRepository.findAllByTimeKey(timeKey));
        }
        return factMovieEntityList;
    }

    @Operation(summary = "通过weekday来查询电影")
    @GetMapping("weekday/{weekday}")
    public List<FactMovieEntity> getByWeekDay(@PathVariable Byte weekday) {
        List<Integer> timeKeyList = divTimeRepository.findAllTimeKeyByWeekday(weekday);
        List<FactMovieEntity> factMovieEntityList = new ArrayList<>();
        for (Integer timeKey: timeKeyList) {
            factMovieEntityList.addAll(factMovieRepository.findAllByTimeKey(timeKey));
        }
        return factMovieEntityList;
    }

}
