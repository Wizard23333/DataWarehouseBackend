package com.datawarehousebackend.mysql.controller;

import com.datawarehousebackend.mysql.model.DivReviewEntity;
import com.datawarehousebackend.mysql.model.DivTimeEntity;
import com.datawarehousebackend.mysql.model.FactMovieEntity;
import com.datawarehousebackend.mysql.repository.DivReviewEntityRepository;
import com.datawarehousebackend.mysql.repository.DivStyleEntityRepository;
import com.datawarehousebackend.mysql.repository.DivTimeRepository;
import com.datawarehousebackend.mysql.repository.FactMovieRepository;
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
import java.util.List;

@RestController
@RequestMapping("api/movie")
@Api(tags = "电影参数接口")
public class MovieController {
    @Resource
    FactMovieRepository factMovieRepository;
    @Resource
    DivTimeRepository divTimeRepository;
    @Resource
    DivReviewEntityRepository divReviewEntityRepository;
    @Resource
    DivStyleEntityRepository divStyleEntityRepository;

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

    @Operation(summary = "通过asin查询电影详细信息")
    @GetMapping("complete-info/{asin}")
    public ResponseEntity<Object> getCompleteInfoByAsin(@PathVariable String asin) {
        CompleteMovieInfo completeMovieInfo = new CompleteMovieInfo();

        FactMovieEntity factMovieEntity = factMovieRepository.findAllByAsin(asin);
        DivTimeEntity divTimeEntity = divTimeRepository.findByTimeKey(factMovieEntity.getTimeKey());
        List<DivReviewEntity> divReviewEntityList = new ArrayList<>();
        divReviewEntityList.addAll(divReviewEntityRepository.findAllByAsin(factMovieEntity.getAsin()));

        completeMovieInfo.setMovieInfo(factMovieEntity);
        completeMovieInfo.setDateTime(divTimeEntity);
        completeMovieInfo.setDivReviewEntityList(divReviewEntityList);
        completeMovieInfo.setStyleName(divStyleEntityRepository.findByStyleKey(factMovieEntity.getStyleKey()).getStyleName());
        return new ResponseEntity<>(completeMovieInfo, HttpStatus.OK);
    }


    @Data
    class CompleteMovieInfo {
        private String asin;
        private String title;
        private String productVersion;
        private double imdbScore;
        private Double reviewPoint;
        private String directorsName;
        private String actorsName;

        private DateTime dateTime;
        private String styleName;

        private List<DivReviewEntity> divReviewEntityList;

        // 初始化一下
        {
            dateTime = new DateTime();
            divReviewEntityList = new ArrayList<>();
        }

        public void setDateTime(DivTimeEntity divTimeEntity) {
            this.dateTime.setArgs(divTimeEntity);
        }

        public void addDivReviewEntityList(List<DivReviewEntity> divReviewEntityList) {
            this.divReviewEntityList.addAll(divReviewEntityList);
        }

        public void setMovieInfo(FactMovieEntity factMovieEntity) {
            this.asin = factMovieEntity.getAsin();
            this.title = factMovieEntity.getTitle();
            this.productVersion = factMovieEntity.getProductVersion();
            this.imdbScore = factMovieEntity.getImdbScore();
            this.reviewPoint = factMovieEntity.getReviewPoint();
            this.directorsName = factMovieEntity.getDirectorsName();
            this.actorsName = factMovieEntity.getActorsName();
        }
    }

    @Data
    class DateTime {
        private short year;
        private Byte month;
        private Byte day;
        private Byte quarter;
        private Byte weekday;

        public void setArgs(DivTimeEntity divTimeEntity) {
            this.year = divTimeEntity.getYear();
            this.month = divTimeEntity.getMonth();
            this.day = divTimeEntity.getDay();
            this.quarter = divTimeEntity.getQuarter();
            this.weekday = divTimeEntity.getWeekday();
        }
    }


}
