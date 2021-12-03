package com.datawarehousebackend.mysql.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DivDirectorEntityPK implements Serializable {
    private int directorId;
    private String directorName;

    @Column(name = "director_id")
    @Id
    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    @Column(name = "director_name")
    @Id
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivDirectorEntityPK that = (DivDirectorEntityPK) o;
        return directorId == that.directorId && Objects.equals(directorName, that.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorId, directorName);
    }
}
