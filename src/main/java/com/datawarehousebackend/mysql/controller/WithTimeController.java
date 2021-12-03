package com.datawarehousebackend.mysql.controller;

import org.hibernate.annotations.GeneratorType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 采用相关时间参数来访问的接口
@RestController
@RequestMapping("api/time")
public class WithTimeController {

    //测试接口
    @GetMapping("test")
    public String helloTest(){
        return "Hello World!";
    }


}
