package com.datawarehousebackend.mysql.controller;

import com.datawarehousebackend.mysql.repository.DivMovieSeriesEntityRepository;
import com.datawarehousebackend.mysql.repository.FactMovieRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/trace")
@Api(tags = "溯源查询接口")
public class TraceController {
    @Resource
    FactMovieRepository factMovieRepository;
    @Resource
    DivMovieSeriesEntityRepository divMovieSeriesEntityRepository;

    @Operation(summary = "找出了多少非电影的数据")
    @GetMapping("non-movie")
    public ResponseEntity<Object> getNonMovieStatus() {
        @Data
        class Status {
            Integer tolReview; //3410019
            Integer tolAsin; // 60176
            Integer tolMovie; // 32929
        }
        Status status = new Status();
        status.setTolReview(3410019);
        status.setTolAsin(60176);
        status.setTolMovie(32929);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @Operation(summary = "通过电影asin查找电影系列数量")
    @GetMapping("movie-series-num/{asin}")
    public ResponseEntity<Object> getMovieSeriesNum(@PathVariable String asin) {
        return new ResponseEntity<>(
                divMovieSeriesEntityRepository.findByMovieSeriesId(
                        factMovieRepository.findByAsin(asin).getMovieSeriesId()
                ), HttpStatus.OK);
    }

    @Operation(summary = "通过电影seriesid查询该系列的电影列表（seriesid从movie-series/{asin}接口获得）")
    @GetMapping("movie-series/{movieSeriesId}")
    public ResponseEntity<Object> getMovieSeries(@PathVariable Integer movieSeriesId) {
        return new ResponseEntity<>(factMovieRepository.findByMovieSeriesId(movieSeriesId), HttpStatus.OK);
    }

}
