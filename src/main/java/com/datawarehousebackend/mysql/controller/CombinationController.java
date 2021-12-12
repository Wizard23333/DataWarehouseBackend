package com.datawarehousebackend.mysql.controller;


import com.datawarehousebackend.mysql.repository.*;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/combination")
@Api(tags = "组合查询接口")
public class CombinationController {
    @Resource
    BridgeDirectEntityRepository bridgeDirectEntityRepository;
    @Resource
    BridgeActEntityRepository bridgeActEntityRepository;
    @Resource
    FactMovieRepository factMovieRepository;
    @Resource
    DivTimeRepository divTimeRepository;
    @Resource
    DivStyleEntityRepository divStyleEntityRepository;



    @Operation(summary = "通过年和id来查询电影asin type = 1表示导演 2表示演员")
    @GetMapping("year-and-id-and-type")
    public ResponseEntity<Object> getMovieAsinByYearAndId(@Param("year") short year, @Param("id") Integer id, @Param("type") Integer type) {
        List<String> asinList = new ArrayList<>();
        if (type == 1) {
            asinList.addAll(bridgeDirectEntityRepository.findAllAsinByDirectorId(id));
        } else {
            asinList.addAll(bridgeActEntityRepository.findAllAsinByActorId(id));
        }

        Map<String, String > returnMap = new Hashtable<>();
        for (String asinItem : asinList) {
            if (divTimeRepository.findByTimeKey(
                    factMovieRepository.findByAsin(asinItem).getTimeKey()
            ).getYear() == year) {
                returnMap.put("asin", asinItem);
            }
        }
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

    @Operation(summary = "通过电影style和id来查询电影asin type = 1表示导演 2表示演员")
    @GetMapping("style-and-id-and-type")
    public ResponseEntity<Object> getMovieAsinByStyleAndId(@Param("styleName") String styleName, @Param("id") Integer id, @Param("type") Integer type) {
        List<String> asinList = new ArrayList<>();
        if (type == 1) {
            asinList.addAll(bridgeDirectEntityRepository.findAllAsinByDirectorId(id));
        } else {
            asinList.addAll(bridgeActEntityRepository.findAllAsinByActorId(id));
        }
        System.out.println(asinList);
        Map<String, String > returnMap = new Hashtable<>();
        for(String asinItem :asinList) {
            System.out.println(divStyleEntityRepository.findByStyleKey(
                    factMovieRepository.findByAsin(asinItem).getStyleKey()
            ).getStyleName());
            if (divStyleEntityRepository.findByStyleKey(
                    factMovieRepository.findByAsin(asinItem).getStyleKey()
            ).getStyleName().equals(styleName)) {
                returnMap.put("asin", asinItem);
            }
        }
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }


}
