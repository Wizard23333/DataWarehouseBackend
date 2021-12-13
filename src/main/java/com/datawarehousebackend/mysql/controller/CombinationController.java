package com.datawarehousebackend.mysql.controller;


import com.datawarehousebackend.mysql.model.FactMovieEntity;
import com.datawarehousebackend.mysql.repository.*;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    DivReviewEntityRepository divReviewEntityRepository;


    @Operation(summary = "通过年和id来查询电影asin type = 1表示导演 2表示演员")
    @GetMapping("year-and-id-and-type")
    public ResponseEntity<Object> getMovieAsinByYearAndId(@Param("year") short year, @Param("id") Integer id, @Param("type") Integer type) {
        List<String> asinList = new ArrayList<>();
        if (type == 1) {
            asinList.addAll(bridgeDirectEntityRepository.findAllAsinByDirectorId(id));
        } else {
            asinList.addAll(bridgeActEntityRepository.findAllAsinByActorId(id));
        }

        Map<String, String> returnMap = new Hashtable<>();
        int num = 0;
        for (String asinItem : asinList) {
            if (divTimeRepository.findByTimeKey(
                    factMovieRepository.findByAsin(asinItem).getTimeKey()
            ).getYear() == year) {
                num++;
                returnMap.put("asin" + num, asinItem);
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
        Map<String, String> returnMap = new Hashtable<>();
        int num = 0;
        for (String asinItem : asinList) {
            System.out.println(divStyleEntityRepository.findByStyleKey(
                    factMovieRepository.findByAsin(asinItem).getStyleKey()
            ).getStyleName());
            if (divStyleEntityRepository.findByStyleKey(
                    factMovieRepository.findByAsin(asinItem).getStyleKey()
            ).getStyleName().equals(styleName)) {
                num++;
                returnMap.put("asin" + num, asinItem);
            }
        }
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

    @Operation(summary = "通过年和类别名来查询电影asin")
    @GetMapping("year-and-style-name")
    public ResponseEntity<Object> getMovieAsinByYearAndStyleName(@Param("year") short year, @Param("styleName") String styleName) {
        List<Integer> timeKeyList = divTimeRepository.findAllTimeKeyByYear(year); // 所有该年的timekey
        Integer styleKey = divStyleEntityRepository.findByStyleName(styleName).getStyleKey(); // 改style的stylekey
        List<String> asinList = new ArrayList<>();
        for (Integer timeKeyItem : timeKeyList) {
            asinList.addAll(factMovieRepository.findAsinByTimeKeyAndStyleKey(timeKeyItem, styleKey));
        }
        Map<String, String> returnMap = new Hashtable<>();
        int num = 0;
        for (String asin : asinList) {
            num++;
            returnMap.put("asin" + num, asin);
        }
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }

    @Operation(summary = "查询类别的imdb平均分 用户平均分")
    @GetMapping("style-avg/{styleName}")
    public ResponseEntity<Object> getAvgScoreByStyleName(@PathVariable("styleName") String styleName) {
        return new ResponseEntity<>(factMovieRepository.findAvgImdbScoreAndReviewPoint(
                divStyleEntityRepository.findByStyleName(styleName).getStyleKey()),
                HttpStatus.OK);
    }

    @Operation(summary = "查询类别的情感平均分")
    @GetMapping("style-avg-motion/{styleName}")
    public ResponseEntity<Object> getAvgSentiment(@PathVariable("styleName") String styleName) {
        List<Double> avgMovieSentiment = new ArrayList<>();
        List<FactMovieEntity> movieList = factMovieRepository.findByStyleKey(
                divStyleEntityRepository.findByStyleName(styleName).getStyleKey() // 查找到styleKey
        );
        for(FactMovieEntity entity : movieList) {
            avgMovieSentiment.add(
                    divReviewEntityRepository.findAvgSentimentByAsin(entity.getAsin()) // 查询每个电影的情感平均分
            );
        }
        Double avgSentiment = avgMovieSentiment.stream().mapToDouble(a -> a).average().orElse(0.0);
        return new ResponseEntity<>(Map.of("avg", avgSentiment), HttpStatus.OK);
    }
}
