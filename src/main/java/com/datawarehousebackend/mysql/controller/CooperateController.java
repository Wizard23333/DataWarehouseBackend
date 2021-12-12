package com.datawarehousebackend.mysql.controller;


import com.datawarehousebackend.mysql.repository.*;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api/cooperate")
@Api(tags = "导演演员合作接口")
public class CooperateController {
    @Resource
    DivActorRepository divActorRepository;
    @Resource
    BridgeActEntityRepository bridgeActEntityRepository;
    @Resource
    DivDirectorRepository divDirectorRepository;
    @Resource
    BridgeDirectEntityRepository bridgeDirectEntityRepository;
    @Resource
    FactMovieRepository factMovieRepository;

    @Operation(summary = "通过两个id查询合作过电影的asin和平均分(type参数 1表示导演 2表示演员)")
    @GetMapping("cooperate-points")
    public ResponseEntity<Object> getCooperatePoints(@Param("id1") int id1, @Param("type1") int type1, @Param("id2") int id2, @Param("type2") int type2) {
        List<String> asinList1 = new LinkedList<>();
        List<String> asinList2 = new LinkedList<>();
        if (type1 == 1) {
            asinList1.addAll(bridgeDirectEntityRepository.findAllAsinByDirectorId(id1));
        } else {
            asinList1.addAll(bridgeActEntityRepository.findAllAsinByActorId(id1));
        }
        if (type2 == 1) {
            asinList2.addAll(bridgeDirectEntityRepository.findAllAsinByDirectorId(id2));
        } else {
            asinList2.addAll(bridgeActEntityRepository.findAllAsinByActorId(id2));
        }

        asinList1.sort(Comparator.naturalOrder());
        asinList2.sort(Comparator.naturalOrder());
        asinList1.retainAll(asinList2); // 求交集

        @Data
        class AsinAndPoints {
            List<String> asinList;
            Double avgPoints;
            {
                asinList = new LinkedList<>();
            }

            public void setArgs(List<String> asinList, Double avgPoints) {
                this.asinList.addAll(asinList);
                this.avgPoints = avgPoints;
            }
        }
        List<Double> pointList = new ArrayList<>();
        for (String item : asinList1) {
            pointList.add(factMovieRepository.findByAsin(item).getImdbScore());
        }
        System.out.println(pointList);
        Double avg = 0D; // 平均值
        for (Double val : pointList) {
            avg += val;
        }
        avg = avg / pointList.size();

        AsinAndPoints asinAndPoints = new AsinAndPoints();
        asinAndPoints.setArgs(asinList1, avg);

        return new ResponseEntity<>(asinAndPoints, HttpStatus.OK);

    }

    @Operation(summary = "top100经常合作的导演和导演")
    @GetMapping("director-director-top100")
    public ResponseEntity<Object> getTop100DirectorDirector() {
        LinkedList<DirectorAndDirector> returnList = new LinkedList<>();
        List<Integer> directorIdList = divDirectorRepository.findAllDirectorId(); // 所有的ID
        directorIdList = directorIdList.subList(0, 500);
        for (Integer item : directorIdList) {
            try {
                DirectorAndDirector dd = new DirectorAndDirector();
//                System.out.println(item);
                dd.setDirectorId1(item);
                dd.setDirectorName1(divDirectorRepository.findByDirectorId(item).getDirectorName());
                // 这里可能会出现空指针异常
//                System.out.println(getCooperateDirectorByDirectorIdApi(item).get(0));
                DirectorIdAndNameAndCopTime mostCopDir = getCooperateDirectorByDirectorIdApi(item).get(0);
                dd.setDirectorId2(mostCopDir.getDirectorId());
                dd.setDirectorName2(mostCopDir.getDirectorName());
                dd.setCopTime(mostCopDir.getCopTime());
                returnList.add(dd);
//                System.out.println(dd);
            } catch (NullPointerException e) {
                continue;
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            // 截取前100个
            if (returnList.size() > 100) {
                returnList.sort(Comparator.reverseOrder()); // 降序排列
                returnList.removeLast(); // 移除最少的
            }
        }
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @Operation(summary = "top100经常合作的演员和演员")
    @GetMapping("actor-actor-top100")
    public ResponseEntity<Object> getTop100ActorActor() {
        LinkedList<ActorAndActor> returnList = new LinkedList<>();
        List<Integer> actorIdList = divActorRepository.findAllActorId();
        actorIdList = actorIdList.subList(0, 300);
        for (Integer item : actorIdList) {
            try {
                ActorAndActor aa = new ActorAndActor();
                aa.setActorId1(item);
                aa.setActorName1(divActorRepository.findByActorId(item).getActorName());

                ActorIdAndNameAndCopTime mostCopActor = getCooperateActorByActorIdApi(item).get(0);
                aa.setActorId2(mostCopActor.getActorId());
                aa.setActorName2(mostCopActor.getActorName());
                aa.setCopTime(mostCopActor.getCopTime());
                returnList.add(aa);

            } catch (NullPointerException e) {
                continue;
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            // 截取前100个
            if (returnList.size() > 100) {
                returnList.sort(Comparator.reverseOrder()); // 降序排列
                returnList.removeLast(); // 移除最少的
            }
        }
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @Operation(summary = "top100经常合作的导演和演员")
    @GetMapping("director-actor-top100")
    public ResponseEntity<Object> getTop100DirectorActor() {
        LinkedList<DirectorAndActor> returnList = new LinkedList<>();
        List<Integer> directorIdList = divDirectorRepository.findAllDirectorId();
        directorIdList = directorIdList.subList(0, 300);
        for (Integer item : directorIdList) {
            try {
                DirectorAndActor da = new DirectorAndActor();
                da.setDirectorId(item);
                da.setDirectorName(divDirectorRepository.findByDirectorId(item).getDirectorName());
                ActorIdAndNameAndCopTime mostCopActor = getCooperateActorByDirectorIdAPi(item).get(0);
                da.setActorId(mostCopActor.getActorId());
                da.setActorName(mostCopActor.getActorName());
                da.setCopTime(mostCopActor.getCopTime());
                returnList.add(da);
            } catch (NullPointerException e) {
                continue;
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            // 截取前100个
            if (returnList.size() > 100) {
                returnList.sort(Comparator.reverseOrder()); // 降序排列
                returnList.removeLast(); // 移除最少的
            }
        }
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    public List<DirectorIdAndNameAndCopTime> getCooperateDirectorByDirectorIdApi(Integer directorId) {
        List<String> asinList = bridgeDirectEntityRepository.findAllAsinByDirectorId(directorId); // 找到导演的电影的asin
        List<Integer> directorIdList = new ArrayList<>();
        for (String item : asinList) {
            directorIdList.addAll(bridgeDirectEntityRepository.findAllDirectorIdByAsin(item)); // 再通过asin查找所有的directorId
        }
        directorIdList.sort(Comparator.naturalOrder());// 升序排列
//        System.out.println(directorIdList);
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
//        System.out.println(actorIdList);
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

    public List<ActorIdAndNameAndCopTime> getCooperateActorByActorIdApi(Integer actorId) {
        List<String> asinList = bridgeActEntityRepository.findAllAsinByActorId(actorId);
        List<Integer> actorIdList = new ArrayList<>();
        for (String item : asinList) {
            actorIdList.addAll(bridgeActEntityRepository.findAllActorIdByAsin(item));
        }
        actorIdList.sort(Comparator.naturalOrder());
//        System.out.println(actorIdList);
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

    @Data
    class DirectorAndDirector implements Comparable<DirectorAndDirector> {
        Integer DirectorId1;
        String DirectorName1;
        Integer DirectorId2;
        String DirectorName2;
        Integer CopTime;

        @Override
        public int compareTo(DirectorAndDirector o) {
            return this.CopTime - o.CopTime;
        }
    }

    @Data
    class DirectorAndActor implements Comparable<DirectorAndActor> {
        Integer DirectorId;
        String DirectorName;
        Integer ActorId;
        String ActorName;
        Integer CopTime;

        @Override
        public int compareTo(DirectorAndActor o) {
            return this.CopTime - o.CopTime;
        }
    }

    @Data
    class ActorAndActor implements Comparable<ActorAndActor> {
        Integer ActorId1;
        String ActorName1;
        Integer ActorId2;
        String ActorName2;
        Integer CopTime;

        @Override
        public int compareTo(ActorAndActor o) {
            return this.CopTime - o.CopTime;
        }
    }

}
