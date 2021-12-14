package com.datawarehousebackend.neo4j.controller;


import com.datawarehousebackend.neo4j.dao.Cooperate_aandaRepository;
import com.datawarehousebackend.neo4j.dao.Cooperate_aanddRepository;
import com.datawarehousebackend.neo4j.dao.Cooperate_dandaRepository;
import com.datawarehousebackend.neo4j.dao.Cooperate_danddRepository;
import com.datawarehousebackend.neo4j.entity.Cooperate_aandd;
import com.datawarehousebackend.neo4j.entity.Cooperate_danda;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/coopneo4j")
@Api(tags = "合作neo4j")
public class AandaController {
    @Resource
    Cooperate_aandaRepository cooperate_aandaRepository;
    @Resource
    Cooperate_aanddRepository cooperate_aanddRepository;
    @Resource
    Cooperate_dandaRepository cooperate_dandaRepository;
    @Resource
    Cooperate_danddRepository cooperate_danddRepository;



    @Operation(summary = "演员和演员合作")
    @GetMapping("aanda")
    public ResponseEntity<Object> getTopaanda() {
        List<Map<String, Object>> test = cooperate_aandaRepository.findTopByName("test");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Integer count = -1;
        for (Map<String, Object> map : test) {
            count = count + 1;
            if (count % 2 == 0) {
                result.add(map);
            }
            if(count>=100){
                break;
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @Operation(summary = "演员和导演合作")
    @GetMapping("aandd")
    public ResponseEntity<Object> getTopaandd() {
        List<Map<String, Object>> test = cooperate_aanddRepository.findTopByName("test");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Integer count = -1;
        for (Map<String, Object> map : test) {
            count = count + 1;

            result.add(map);

            if(count>=50){
                break;
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }


    @Operation(summary = "导演和演员合作")
    @GetMapping("danda")
    public ResponseEntity<Object> getTopdanda() {
        List<Map<String, Object>> test = cooperate_dandaRepository.findTopByName("test");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Integer count = -1;
        for (Map<String, Object> map : test) {
            count = count + 1;
                result.add(map);
            if(count>=50){
                break;
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }


    @Operation(summary = "导演和导演合作")
    @GetMapping("dandd")
    public ResponseEntity<Object> getTopdandd() {
        List<Map<String, Object>> test = cooperate_danddRepository.findTopByName("test");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Integer count = -1;
        for (Map<String, Object> map : test) {
            count = count + 1;
            if (count % 2 == 0) {
                result.add(map);
            }
            if(count>=100){
                break;
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
