package com.datawarehousebackend.mysql.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DivActorEntityPK implements Serializable {
    private int actorId;
    private String actorName;

    @Column(name = "actor_id")
    @Id
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Column(name = "actor_name")
    @Id
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivActorEntityPK that = (DivActorEntityPK) o;
        return actorId == that.actorId && Objects.equals(actorName, that.actorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, actorName);
    }
}
