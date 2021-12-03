package com.datawarehousebackend.mysql.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DivStyleEntityPK implements Serializable {
    private int styleKey;
    private String styleName;

    @Column(name = "style_key")
    @Id
    public int getStyleKey() {
        return styleKey;
    }

    public void setStyleKey(int styleKey) {
        this.styleKey = styleKey;
    }

    @Column(name = "style_name")
    @Id
    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivStyleEntityPK that = (DivStyleEntityPK) o;
        return styleKey == that.styleKey && Objects.equals(styleName, that.styleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(styleKey, styleName);
    }
}
