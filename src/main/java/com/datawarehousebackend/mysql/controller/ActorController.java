package com.datawarehousebackend.mysql.controller;

import com.datawarehousebackend.mysql.repository.DivActorRepository;
import com.datawarehousebackend.mysql.repository.DivDirectorRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public List<String> getMoviesByActorName(@PathVariable String actorName) {
        return divActorRepository.findAllMovieNameByActorName(actorName);
    }

    @Operation(summary = "通过actorName模糊查询演过的电影")
    @GetMapping("fuzzy-name/{actorName}")
    public List<String> getFuzzyActorNameByActorName(@PathVariable String actorName) {
        return divActorRepository.findAllFuzzyActorNameByActorName(actorName);
    }


}
