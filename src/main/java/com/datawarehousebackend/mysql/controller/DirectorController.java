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
@RequestMapping("api/director")
@Api(tags = "导演参数接口")
public class DirectorController {
    @Resource
    DivDirectorRepository divDirectorRepository;

    @Resource
    DivActorRepository divActorRepository;

    @Operation(summary = "通过directorName来查询导演过的电影")
    @GetMapping("movies-name/{directorName}")
    public List<String> getMoviesByDirectorName(@PathVariable String directorName) {
        return divDirectorRepository.findAllMovieNameByDirectorName(directorName);
    }

    // 模糊查询导演名
    // 待定
    @Operation(summary = "通过directorName模糊查询导演名")
    @GetMapping("fuzzy-name/{directorName}")
    public List<Object> getFuzzyDirectorNameByDirectorName(@PathVariable String directorName) {
        return divDirectorRepository.findAllFuzzyDirectorNameByDirectorName(directorName);
    }

//    @Operation(summary = "通过directorId查询经常合作的导演")
//    @GetMapping("")

}
