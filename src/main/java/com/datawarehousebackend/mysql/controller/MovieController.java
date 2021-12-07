package com.datawarehousebackend.mysql.controller;

import com.datawarehousebackend.mysql.model.FactMovieEntity;
import com.datawarehousebackend.mysql.repository.FactMovieRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/movie")
@Api(tags = "电影参数接口")
public class MovieController {
    @Resource
    FactMovieRepository factMovieRepository;

    @Operation(summary = "通过完整的title来查询电影")
    @GetMapping("complete-title/{title}")
    public List<FactMovieEntity> getByCompleteTitle(@PathVariable String title) {
        return factMovieRepository.findAllByTitle(title);
    }

    @Operation(summary = "通过模糊匹配title来查询电影，（查询包含title的电影）")
    @GetMapping("fuzzy-title/{title}")
    public List<FactMovieEntity> getByFuzzyTitle(@PathVariable String title) {
        return factMovieRepository.findAllByTitleContaining(title);
    }


}
