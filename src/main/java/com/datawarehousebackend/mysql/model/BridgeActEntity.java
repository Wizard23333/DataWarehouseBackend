package com.datawarehousebackend.mysql.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bridge_act", schema = "dataWarehouseDB", catalog = "")
@IdClass(BridgeActEntityPK.class)
public class BridgeActEntity {
    private String asin;
    private int actorId;

    @Id
    @Column(name = "asin")
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @Id
    @Column(name = "actor_id")
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
        BridgeActEntity that = (BridgeActEntity) o;
        return actorId == that.actorId && Objects.equals(asin, that.asin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asin, actorId);
    }
}
