package com.datawarehousebackend.mysql.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fact_movie", schema = "dataWarehouseDB", catalog = "")
public class FactMovieEntity {
    private String asin;
    private String title;
    private String productVersion;
    private double imdbScore;
    private Double reviewPoint;
    private String directorsName;
    private String actorsName;

    @Id
    @Column(name = "asin")
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactMovieEntity that = (FactMovieEntity) o;
        return Double.compare(that.imdbScore, imdbScore) == 0 && Objects.equals(asin, that.asin) && Objects.equals(title, that.title) && Objects.equals(productVersion, that.productVersion) && Objects.equals(reviewPoint, that.reviewPoint) && Objects.equals(directorsName, that.directorsName) && Objects.equals(actorsName, that.actorsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asin, title, productVersion, imdbScore, reviewPoint, directorsName, actorsName);
    }
}
