package com.datawarehousebackend.mysql.controller;

import com.datawarehousebackend.mysql.repository.DivActorRepository;
import com.datawarehousebackend.mysql.repository.DivDirectorRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/actor")
@Api(tags = "演员参数接口")
public class ActorController {

    @Resource
    DivActorRepository divActorRepository;
    @Resource
    DivDirectorRepository divDirectorRepository;

    @Operation(summary = "通过actorName来查询电影")
    @GetMapping("movies-name/{actorName}")
    public ResponseEntity<Object> getMoviesByActorName(@PathVariable String actorName) {
        return new ResponseEntity<>(divActorRepository.findAllMovieNameByActorName(actorName), HttpStatus.OK);
    }

    @Operation(summary = "通过actorName模糊查询演员")
    @GetMapping("fuzzy-name/{actorName}")
    public ResponseEntity<Object> getFuzzyActorNameByActorName(@PathVariable String actorName) {
        return new ResponseEntity<>(divActorRepository.findAllFuzzyActorNameByActorName(actorName), HttpStatus.OK);
    }


}
