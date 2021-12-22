package com.datawarehousebackend.neo4j.controller;


import com.datawarehousebackend.neo4j.dao.*;
import com.datawarehousebackend.neo4j.entity.Cooperate_aandd;
import com.datawarehousebackend.neo4j.entity.Cooperate_danda;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Resource
    TimeRepository timeRepository;


    @Operation(summary = "演员和演员合作")
    @GetMapping("aanda")
    public ResponseEntity<Object> getTopaanda() {
        List<Map<String, Object>> test = cooperate_aandaRepository.findTopByName("test");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Integer count = -1;
        for (Map<String, Object> map : test) {
            count = count + 1;
            Map<String, Object> temp = new HashMap<String, Object>();
            for (String key : map.keySet()) {
                String tempkey = key.replace(".", "");
                String tempvalue = map.get(key).toString();
                temp.put(tempkey, tempvalue);
            }
            if (count % 2 == 0) {
                result.add(temp);
            }
            if (count >= 100) {
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
            Map<String, Object> temp = new HashMap<String, Object>();
            for (String key : map.keySet()) {
                String tempkey = key.replace(".", "");
                String tempvalue = map.get(key).toString();
                temp.put(tempkey, tempvalue);
            }
            count = count + 1;

            result.add(temp);

            if (count >= 50) {
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
            Map<String, Object> temp = new HashMap<String, Object>();
            for (String key : map.keySet()) {
                String tempkey = key.replace(".", "");
                String tempvalue = map.get(key).toString();
                temp.put(tempkey, tempvalue);
            }
            count = count + 1;
            result.add(temp);
            if (count >= 50) {
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
            Map<String, Object> temp = new HashMap<String, Object>();
            for (String key : map.keySet()) {
                String tempkey = key.replace(".", "");
                String tempvalue = map.get(key).toString();
                temp.put(tempkey, tempvalue);
            }
            count = count + 1;
            if (count % 2 == 0) {
                result.add(temp);
            }
            if (count >= 100) {
                break;
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @Operation(summary = "通过年份查询")
    @GetMapping("year")
    public ResponseEntity<Object> getTopyear(@Param("year") String yearid) {
        List<Map<String, Object>> test = timeRepository.findTopByyear("test");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Integer count = -1;
        for (Map<String, Object> map : test) {
            Map<String, Object> temp = new HashMap<String, Object>();
            for (String key : map.keySet()) {
                String tempkey = key.replace(".", "");
                String tempvalue = map.get(key).toString();
                temp.put(tempkey, tempvalue);
            }

            String str = temp.get("bname[2]").toString();
            String strname = temp.get("aname").toString();
            if (str.equals(yearid)) {
                count = count + 1;
                System.out.println(count);
                List<String> topByyear1 = timeRepository.findTopByyear1(strname);
                temp.put("dir", topByyear1);
                List<String> topByyear2 = timeRepository.findTopByyear2(strname);
                temp.put("act", topByyear2);
                List<String> topByyear3 = timeRepository.findTopByyear3(strname);
                temp.put("sty", topByyear3);
                List<String> topByyear4 = timeRepository.findTopByyear4(strname);
                temp.put("ver", topByyear4);
                result.add(temp);
            }

        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "通过年份月份查询")
    @GetMapping("year_month")
    public ResponseEntity<Object> getTopyearmonth(@Param("year") String yearid, @Param("month") String monthid) {
        String monthstr = null;
        if (monthid.equals("1")) {
            monthstr = "January";
        }
        if (monthid.equals("2")) {
            monthstr = "February";
        }
        if (monthid.equals("3")) {
            monthstr = "March";
        }
        if (monthid.equals("4")) {
            monthstr = "April";
        }
        if (monthid.equals("5")) {
            monthstr = "May";
        }
        if (monthid.equals("6")) {
            monthstr = "June";
        }
        if (monthid.equals("7")) {
            monthstr = "July";
        }
        if (monthid.equals("8")) {
            monthstr = "August";
        }
        if (monthid.equals("9")) {
            monthstr = "September";
        }
        if (monthid.equals("10")) {
            monthstr = "October";
        }
        if (monthid.equals("11")) {
            monthstr = "November";
        }
        if (monthid.equals("12")) {
            monthstr = "December";
        }
        List<Map<String, Object>> test = timeRepository.findTopByyear("test");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Integer count = -1;
        for (Map<String, Object> map : test) {
            Map<String, Object> temp = new HashMap<String, Object>();
            for (String key : map.keySet()) {
                String tempkey = key.replace(".", "");
                String tempvalue = map.get(key).toString();
                temp.put(tempkey, tempvalue);
            }
            count = count + 1;
            String str = temp.get("bname[2]").toString();
            String str1 = temp.get("bname[0]").toString();
            String strname = temp.get("aname").toString();
            if (str.equals(yearid) && str1.equals(monthstr)) {
                List<String> topByyear1 = timeRepository.findTopByyear1(strname);
                temp.put("dir", topByyear1);
                List<String> topByyear2 = timeRepository.findTopByyear2(strname);
                temp.put("act", topByyear2);
                List<String> topByyear3 = timeRepository.findTopByyear3(strname);
                temp.put("sty", topByyear3);
                List<String> topByyear4 = timeRepository.findTopByyear4(strname);
                temp.put("ver", topByyear4);
                result.add(temp);
            }

        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "通过年份月份日查询")
    @GetMapping("year_month_day")
    public ResponseEntity<Object> getTopyearmonthday(@Param("year") String yearid, @Param("month") String monthid, @Param("year") String dayid) {
        String monthstr = null;
        if (monthid.equals("1")) {
            monthstr = "January";
        }
        if (monthid.equals("2")) {
            monthstr = "February";
        }
        if (monthid.equals("3")) {
            monthstr = "March";
        }
        if (monthid.equals("4")) {
            monthstr = "April";
        }
        if (monthid.equals("5")) {
            monthstr = "May";
        }
        if (monthid.equals("6")) {
            monthstr = "June";
        }
        if (monthid.equals("7")) {
            monthstr = "July";
        }
        if (monthid.equals("8")) {
            monthstr = "August";
        }
        if (monthid.equals("9")) {
            monthstr = "September";
        }
        if (monthid.equals("10")) {
            monthstr = "October";
        }
        if (monthid.equals("11")) {
            monthstr = "November";
        }
        if (monthid.equals("12")) {
            monthstr = "December";
        }
        List<Map<String, Object>> test = timeRepository.findTopByyear("test");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Integer count = -1;
        for (Map<String, Object> map : test) {
            Map<String, Object> temp = new HashMap<String, Object>();
            for (String key : map.keySet()) {
                String tempkey = key.replace(".", "");
                String tempvalue = map.get(key).toString();
                temp.put(tempkey, tempvalue);
            }
            count = count + 1;
            String str = temp.get("bname[2]").toString();
            String str1 = temp.get("bname[0]").toString();
            String str2 = temp.get("bname[1]").toString();
            String strname = temp.get("aname").toString();
            if (str.equals(yearid) && str1.equals(monthstr) && str2.equals(dayid)) {
                List<String> topByyear1 = timeRepository.findTopByyear1(strname);
                temp.put("dir", topByyear1);
                List<String> topByyear2 = timeRepository.findTopByyear2(strname);
                temp.put("act", topByyear2);
                List<String> topByyear3 = timeRepository.findTopByyear3(strname);
                temp.put("sty", topByyear3);
                List<String> topByyear4 = timeRepository.findTopByyear4(strname);
                temp.put("ver", topByyear4);
                result.add(temp);
            }

        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
