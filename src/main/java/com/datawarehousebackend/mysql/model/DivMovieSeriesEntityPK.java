package com.datawarehousebackend.mysql.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DivMovieSeriesEntityPK implements Serializable {
    private int movieSeriesId;
    private String seriesName;

    @Column(name = "movie_series_id")
    @Id
    public int getMovieSeriesId() {
        return movieSeriesId;
    }

    public void setMovieSeriesId(int movieSeriesId) {
        this.movieSeriesId = movieSeriesId;
    }

    @Column(name = "series_name")
    @Id
    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivMovieSeriesEntityPK that = (DivMovieSeriesEntityPK) o;
        return movieSeriesId == that.movieSeriesId && Objects.equals(seriesName, that.seriesName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieSeriesId, seriesName);
    }
}
