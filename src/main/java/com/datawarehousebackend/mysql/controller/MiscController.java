package com.datawarehousebackend.mysql.controller;


import com.datawarehousebackend.mysql.repository.DivStyleEntityRepository;
import com.datawarehousebackend.mysql.repository.FactMovieRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("api/misc")
@Api(tags = "杂项接口")
public class MiscController {
    @Resource
    DivStyleEntityRepository divStyleEntityRepository;
    @Resource
    FactMovieRepository factMovieRepository;

    @Operation(summary = "通过精确的styleName查询这个类别的电影数量")
    @GetMapping("style-movie-num/{styleName}")
    public ResponseEntity<Object> getStyleMovieNumByStyleName(@PathVariable String styleName) {
        return new ResponseEntity<>(divStyleEntityRepository.findByStyleName(styleName), HttpStatus.OK);
    }

    @Operation(summary = "查询前一百条类型的结果")
    @GetMapping("top100-style-movie-num")
    public ResponseEntity<Object> getTop100StyleMovieNum() {
        return new ResponseEntity<>(divStyleEntityRepository.findTop100ByOrderByStyleNumDesc(), HttpStatus.OK);
    }

    @Operation(summary = "查询大于等于该分数的电影数量")
    @GetMapping("movie-num-more-than-score/{score}")
    public ResponseEntity<Object> getMovieNumByMoreThanScore(@PathVariable Double score) {
        return new ResponseEntity<>(Map.of("number",factMovieRepository.findMovieNumByMoreThanScore(score)) , HttpStatus.OK);
    }


}
