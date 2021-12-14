package com.datawarehousebackend;

import com.datawarehousebackend.neo4j.dao.ActorRepository;
import com.datawarehousebackend.neo4j.dao.Cooperate_aandaRepository;
import com.datawarehousebackend.neo4j.entity.Cooperate_aanda;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class DataWarehouseBackendApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    ActorRepository actorRepository;

    @Autowired
    Cooperate_aandaRepository cooperate_aandaRepository;

    @Test
    public void test(){


//        long count = actorRepository.count();
//        System.out.println(count);
//        Optional<Actor> byId = actorRepository.findById(247748L);
//        System.out.println("sfd");
//        Iterable<Actor> all = actorRepository.findAll();
//        System.out.println("fsda");
//        Iterable<Cooperate_aanda> all = cooperate_aandaRepository.findAll();
//        System.out.println("sdfa");
//        Map<String, String> gettop1 = actorRepository.gettop();
//        List<String> gettop = gettop1

//        List<Map<String,Object>>alvin_alexis = actorRepository.findByname("Alvin Alexis");
//        System.out.println("getValue="+alvin_alexis.keySet());
//        Map<String, String> gettop = cooperate_aandaRepository.gettop();
//        System.out.println(cooperate_aandaRepository.gettop().toString());
//        System.out.println("dsg");


    }
}
