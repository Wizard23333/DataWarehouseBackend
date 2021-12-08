package com.datawarehousebackend.mysql.controller;


import com.datawarehousebackend.mysql.repository.DivActorRepository;
import com.datawarehousebackend.mysql.repository.DivDirectorRepository;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api/cooperate")
@Api(tags = "导演演员合作接口")
public class CooperateController {
    @Resource
    DivActorRepository divActorRepository;
    @Resource
    DivDirectorRepository divDirectorRepository;


}
