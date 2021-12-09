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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("api/director")
@Api(tags = "导演参数接口")
public class DirectorController {
    @Resource
    DivDirectorRepository divDirectorRepository;

    @Resource
    BridgeDirectEntityRepository bridgeDirectEntityRepository;

    @Resource
    DivActorRepository divActorRepository;

    @Resource
    BridgeActEntityRepository bridgeActEntityRepository;

    @Operation(summary = "通过directorId来查询导演过的电影")
    @GetMapping("movies-name/{directorId}")
    public ResponseEntity<Object> getMoviesByDirectorName(@PathVariable Integer directorId) {
        return new ResponseEntity<>(divDirectorRepository.findAllMovieNameByDirectorName(directorId), HttpStatus.OK);
    }

    // 模糊查询导演名
    // 待定
    @Operation(summary = "通过directorName模糊查询导演名和directorId")
    @GetMapping("fuzzy-name/{directorName}")
    public ResponseEntity<Object> getFuzzyDirectorNameByDirectorName(@PathVariable String directorName) {
        return new ResponseEntity<>(divDirectorRepository.findAllFuzzyDirectorNameByDirectorName(directorName), HttpStatus.OK);
    }

    @Operation(summary = "通过directorId查询经常合作的导演")
    @GetMapping("cooperate-director/{directorId}")
    public ResponseEntity<Object> getCooperateDirectorByDirectorId(@PathVariable Integer directorId) {
        List<DirectorIdAndNameAndCopTime> returnList = getCooperateDirectorByDirectorIdApi(directorId);
        System.out.println(returnList);
        returnList.sort(Comparator.reverseOrder()); // 降序排列
        // 截取前十个
        if (returnList.size() < 11) {
            return new ResponseEntity<>(returnList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnList.subList(0, 10), HttpStatus.OK);
        }

    }

    @Operation(summary = "通过directorId查询经常合作的演员")
    @GetMapping("cooperate-actor/{directorId}")
    public ResponseEntity<Object> getCooperateActorByDirectorId(@PathVariable Integer directorId) {
        List<ActorIdAndNameAndCopTime> returnList = getCooperateActorByDirectorIdAPi(directorId);
        System.out.println(returnList);
        returnList.sort(Comparator.reverseOrder());
        if (returnList.size() < 11) {
            return new ResponseEntity<>(returnList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(returnList.subList(0, 10), HttpStatus.OK);
        }
    }

    public List<DirectorIdAndNameAndCopTime> getCooperateDirectorByDirectorIdApi(Integer directorId) {
        List<String> asinList = bridgeDirectEntityRepository.findAllAsinByDirectorId(directorId); // 找到导演的电影的asin
        List<Integer> directorIdList = new ArrayList<>();
        for (String item : asinList) {
            directorIdList.addAll(bridgeDirectEntityRepository.findAllDirectorIdByAsin(item)); // 再通过asin查找所有的directorId
        }
        directorIdList.sort(Comparator.naturalOrder());// 升序排列
        System.out.println(directorIdList);
        // inner class
        List<DirectorIdAndNameAndCopTime> returnList = new ArrayList<>();
        for (int i = 0; i < directorIdList.size(); i++) {
//            System.out.printf("%d, %d\n", directorIdList.get(i), directorId);
            // integer类型的比较需要使用equals
            // 排除自己的id
            if (!directorIdList.get(i).equals(directorId)) {
                // 和前一个不一样的话
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
        }
        return returnList;
    }

    public List<ActorIdAndNameAndCopTime> getCooperateActorByDirectorIdAPi(Integer directorId) {
        List<String> asinList = bridgeDirectEntityRepository.findAllAsinByDirectorId(directorId);
        List<Integer> actorIdList = new ArrayList<>();
        for (String item : asinList) {
            actorIdList.addAll(bridgeActEntityRepository.findAllActorIdByAsin(item));
        }
        actorIdList.sort(Comparator.naturalOrder());
        System.out.println(actorIdList);
        List<ActorIdAndNameAndCopTime> returnList = new ArrayList<>();
        for (int i = 0; i < actorIdList.size(); i++) {
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
        return returnList;
    }

    @Data
    class DirectorIdAndNameAndCopTime implements Comparable<DirectorIdAndNameAndCopTime> {
        Integer directorId;
        String directorName;
        Integer CopTime;

        @Override
        public int compareTo(DirectorIdAndNameAndCopTime o) {
            return this.CopTime - o.CopTime;
        }
    }

    @Data
    class ActorIdAndNameAndCopTime implements Comparable<ActorIdAndNameAndCopTime> {
        Integer actorId;
        String actorName;
        Integer CopTime;

        @Override
        public int compareTo(ActorIdAndNameAndCopTime o) {
            return this.CopTime - o.CopTime;
        }
    }


}
