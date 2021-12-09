package com.datawarehousebackend.mysql.controller;

import com.datawarehousebackend.mysql.repository.BridgeActEntityRepository;
import com.datawarehousebackend.mysql.repository.BridgeDirectEntityRepository;
import com.datawarehousebackend.mysql.repository.DivActorRepository;
import com.datawarehousebackend.mysql.repository.DivDirectorRepository;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("api/actor")
@Api(tags = "演员参数接口")
public class ActorController {

    @Resource
    DivActorRepository divActorRepository;
    @Resource
    BridgeActEntityRepository bridgeActEntityRepository;
    @Resource
    DivDirectorRepository divDirectorRepository;
    @Resource
    BridgeDirectEntityRepository bridgeDirectEntityRepository;

    @Operation(summary = "通过actorId来查询电影")
    @GetMapping("movies-name/{actorId}")
    public ResponseEntity<Object> getMoviesByActorName(@PathVariable Integer actorId) {
        return new ResponseEntity<>(divActorRepository.findAllMovieNameByActorName(actorId), HttpStatus.OK);
    }

    @Operation(summary = "通过actorName模糊查询演员名和演员Id")
    @GetMapping("fuzzy-name/{actorName}")
    public ResponseEntity<Object> getFuzzyActorNameByActorName(@PathVariable String actorName) {
        return new ResponseEntity<>(divActorRepository.findAllFuzzyActorNameByActorName(actorName), HttpStatus.OK);
    }

    @Operation(summary = "通过actorId查询经常合作的演员")
    @GetMapping("cooperate-actor/{actorId}")
    public ResponseEntity<Object> getCooperateActorByActorId(@PathVariable Integer actorId) {
        List<ActorIdAndNameAndCopTime> returnList = getCooperateActorByActorIdApi(actorId);
        System.out.println(returnList);
        returnList.sort(Comparator.reverseOrder());
        if (returnList.size() < 11) {
            return new ResponseEntity<>(returnList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnList.subList(0, 10), HttpStatus.OK);
        }
    }

    @Operation(summary = "通过actorId查询经常合作的导演")
    @GetMapping("cooperate-director/{actorId}")
    public ResponseEntity<Object> getCooperateDirectorByActorId(@PathVariable Integer actorId) {
        List<DirectorIdAndNameAndCopTime> returnList = getCooperateDirectorByActorIdApi(actorId);
        System.out.println(returnList);
        returnList.sort(Comparator.reverseOrder()); // 降序排列
        // 截取前十个
        if (returnList.size() < 11) {
            return new ResponseEntity<>(returnList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnList.subList(0, 10), HttpStatus.OK);
        }
    }

    public List<ActorIdAndNameAndCopTime> getCooperateActorByActorIdApi(Integer actorId) {
        List<String> asinList = bridgeActEntityRepository.findAllAsinByActorId(actorId);
        List<Integer> actorIdList = new ArrayList<>();
        for (String item : asinList) {
            actorIdList.addAll(bridgeActEntityRepository.findAllActorIdByAsin(item));
        }
        actorIdList.sort(Comparator.naturalOrder());
        System.out.println(actorIdList);
        List<ActorIdAndNameAndCopTime> returnList = new ArrayList<>();
        for (int i = 0; i < actorIdList.size(); i++) {
            if (!actorIdList.get(i).equals(actorId)) {
                if (i == 0 || !actorIdList.get(i).equals(actorIdList.get(i - 1))) {
                    ActorIdAndNameAndCopTime returnItem = new ActorIdAndNameAndCopTime();
                    returnItem.setActorId(actorIdList.get(i));
                    returnItem.setActorName(divActorRepository.findByActorId(actorIdList.get(i)).getActorName());
                    returnItem.setCopTime(1);
                    returnList.add(returnItem);
                } else {
                    int length = returnList.size();
                    ActorIdAndNameAndCopTime lastItem = returnList.get(length - 1);
                    lastItem.setCopTime(lastItem.getCopTime() + 1);
                    returnList.set(length - 1, lastItem);
                }
            }
        }
        return returnList;
    }

    public List<DirectorIdAndNameAndCopTime> getCooperateDirectorByActorIdApi(Integer actorId) {
        List<String> asinList = bridgeActEntityRepository.findAllAsinByActorId(actorId);
        List<Integer> directorIdList = new ArrayList<>();
        for (String item : asinList) {
            directorIdList.addAll(bridgeDirectEntityRepository.findAllDirectorIdByAsin(item));
        }
        directorIdList.sort(Comparator.naturalOrder());
        System.out.println(directorIdList);

        List<DirectorIdAndNameAndCopTime> returnList = new ArrayList<>();
        for (int i = 0; i < directorIdList.size(); i++) {
            if (i == 0 || !directorIdList.get(i).equals(directorIdList.get(i - 1))) {
                DirectorIdAndNameAndCopTime returnItem = new DirectorIdAndNameAndCopTime();
                returnItem.setDirectorId(directorIdList.get(i));
                returnItem.setDirectorName(divDirectorRepository.findByDirectorId(directorIdList.get(i)).getDirectorName());
                returnItem.setCopTime(1);
                returnList.add(returnItem);
            } else {
                // 和前一个不一样的话
                int length = returnList.size();
                DirectorIdAndNameAndCopTime lastItem = returnList.get(length - 1);
                lastItem.setCopTime(lastItem.getCopTime() + 1); // 合作次数加1
                returnList.set(length - 1, lastItem);
            }
        }
        return returnList;
    }

    @Data
    public class ActorIdAndNameAndCopTime implements Comparable<ActorIdAndNameAndCopTime> {
        Integer actorId;
        String actorName;
        Integer CopTime;

        @Override
        public int compareTo(ActorIdAndNameAndCopTime o) {
            return this.CopTime - o.CopTime;
        }
    }

    @Data
    public class DirectorIdAndNameAndCopTime implements Comparable<DirectorIdAndNameAndCopTime> {
        Integer directorId;
        String directorName;
        Integer CopTime;

        @Override
        public int compareTo(DirectorIdAndNameAndCopTime o) {
            return this.CopTime - o.CopTime;
        }
    }


}
