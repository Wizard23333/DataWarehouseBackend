package com.datawarehousebackend.mysql.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fact_movie", schema = "dataWarehouseDB", catalog = "")
public class FactMovieEntity {
    private String asin;
    private Integer movieSeriesId;
    private String title;
    private String productVersion;
    private double imdbScore;
    private Double reviewPoint;
    private Integer timeKey;
    private Integer styleKey;
    private String directorsName;
    private String actorsName;
    private String moviePic;

    @Id
    @Column(name = "asin")
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @Basic
    @Column(name = "movie_series_id")
    public Integer getMovieSeriesId() {
        return movieSeriesId;
    }

    public void setMovieSeriesId(Integer movieSeriesId) {
        this.movieSeriesId = movieSeriesId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "product_version")
    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    @Basic
    @Column(name = "imdb_score")
    public double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    @Basic
    @Column(name = "review_point")
    public Double getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(Double reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

    @Basic
    @Column(name = "time_key")
    public Integer getTimeKey() {
        return timeKey;
    }

    public void setTimeKey(Integer timeKey) {
        this.timeKey = timeKey;
    }

    @Basic
    @Column(name = "style_key")
    public Integer getStyleKey() {
        return styleKey;
    }

    public void setStyleKey(Integer styleKey) {
        this.styleKey = styleKey;
    }

    @Basic
    @Column(name = "directors_name")
    public String getDirectorsName() {
        return directorsName;
    }

    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    @Basic
    @Column(name = "actors_name")
    public String getActorsName() {
        return actorsName;
    }

    public void setActorsName(String actorsName) {
        this.actorsName = actorsName;
    }

    @Basic
    @Column(name = "movie_pic")
    public String getMoviePic() {
        return moviePic;
    }

    public void setMoviePic(String moviePic) {
        this.moviePic = moviePic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactMovieEntity that = (FactMovieEntity) o;
        return Double.compare(that.imdbScore, imdbScore) == 0 && Objects.equals(asin, that.asin) && Objects.equals(movieSeriesId, that.movieSeriesId) && Objects.equals(title, that.title) && Objects.equals(productVersion, that.productVersion) && Objects.equals(reviewPoint, that.reviewPoint) && Objects.equals(timeKey, that.timeKey) && Objects.equals(styleKey, that.styleKey) && Objects.equals(directorsName, that.directorsName) && Objects.equals(actorsName, that.actorsName) && Objects.equals(moviePic, that.moviePic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asin, movieSeriesId, title, productVersion, imdbScore, reviewPoint, timeKey, styleKey, directorsName, actorsName, moviePic);
    }
}
