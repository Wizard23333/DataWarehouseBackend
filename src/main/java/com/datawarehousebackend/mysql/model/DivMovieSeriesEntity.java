package com.datawarehousebackend.mysql.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "div_movie_series", schema = "dataWarehouseDB", catalog = "")
@IdClass(DivMovieSeriesEntityPK.class)
public class DivMovieSeriesEntity {
    private int movieSeriesId;
    private String seriesName;
    private byte seriesNum;

    @Id
    @Column(name = "movie_series_id")
    public int getMovieSeriesId() {
        return movieSeriesId;
    }

    public void setMovieSeriesId(int movieSeriesId) {
        this.movieSeriesId = movieSeriesId;
    }

    @Id
    @Column(name = "series_name")
    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    @Basic
    @Column(name = "series_num")
    public byte getSeriesNum() {
        return seriesNum;
    }

    public void setSeriesNum(byte seriesNum) {
        this.seriesNum = seriesNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivMovieSeriesEntity that = (DivMovieSeriesEntity) o;
        return movieSeriesId == that.movieSeriesId && seriesNum == that.seriesNum && Objects.equals(seriesName, that.seriesName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieSeriesId, seriesName, seriesNum);
    }
}
