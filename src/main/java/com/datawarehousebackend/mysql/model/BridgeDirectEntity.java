package com.datawarehousebackend.mysql.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bridge_direct", schema = "dataWarehouseDB", catalog = "")
@IdClass(BridgeDirectEntityPK.class)
public class BridgeDirectEntity {
    private String asin;
    private int directorId;

    @Id
    @Column(name = "asin")
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @Id
    @Column(name = "director_id")
    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BridgeDirectEntity that = (BridgeDirectEntity) o;
        return directorId == that.directorId && Objects.equals(asin, that.asin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asin, directorId);
    }
}
