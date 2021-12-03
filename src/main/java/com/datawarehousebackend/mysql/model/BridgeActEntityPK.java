package com.datawarehousebackend.mysql.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BridgeActEntityPK implements Serializable {
    private String asin;
    private int actorId;

    @Column(name = "asin")
    @Id
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @Column(name = "actor_id")
    @Id
    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BridgeActEntityPK that = (BridgeActEntityPK) o;
        return actorId == that.actorId && Objects.equals(asin, that.asin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asin, actorId);
    }
}
