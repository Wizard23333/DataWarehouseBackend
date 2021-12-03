package com.datawarehousebackend.mysql.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "div_director", schema = "dataWarehouseDB", catalog = "")
@IdClass(DivDirectorEntityPK.class)
public class DivDirectorEntity {
    private int directorId;
    private String directorName;
    private String movieName;

    @Id
    @Column(name = "director_id")
    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    @Id
    @Column(name = "director_name")
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Basic
    @Column(name = "movie_name")
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivDirectorEntity that = (DivDirectorEntity) o;
        return directorId == that.directorId && Objects.equals(directorName, that.directorName) && Objects.equals(movieName, that.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorId, directorName, movieName);
    }
}
