package com.datawarehousebackend.mysql.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "div_actor", schema = "dataWarehouseDB", catalog = "")
@IdClass(DivActorEntityPK.class)
public class DivActorEntity {
    private int actorId;
    private String actorName;
    private String movieName;

    @Id
    @Column(name = "actor_id")
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Id
    @Column(name = "actor_name")
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
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
        DivActorEntity that = (DivActorEntity) o;
        return actorId == that.actorId && Objects.equals(actorName, that.actorName) && Objects.equals(movieName, that.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, actorName, movieName);
    }
}
