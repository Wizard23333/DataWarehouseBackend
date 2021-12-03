package com.datawarehousebackend.mysql.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BridgeDirectEntityPK implements Serializable {
    private String asin;
    private int directorId;

    @Column(name = "asin")
    @Id
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @Column(name = "director_id")
    @Id
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
        BridgeDirectEntityPK that = (BridgeDirectEntityPK) o;
        return directorId == that.directorId && Objects.equals(asin, that.asin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asin, directorId);
    }
}
