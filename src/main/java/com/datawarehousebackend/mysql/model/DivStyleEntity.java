package com.datawarehousebackend.mysql.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "div_style", schema = "dataWarehouseDB", catalog = "")
@IdClass(DivStyleEntityPK.class)
public class DivStyleEntity {
    private int styleKey;
    private String styleName;
    private Integer styleNum;

    @Id
    @Column(name = "style_key")
    public int getStyleKey() {
        return styleKey;
    }

    public void setStyleKey(int styleKey) {
        this.styleKey = styleKey;
    }

    @Id
    @Column(name = "style_name")
    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    @Basic
    @Column(name = "style_num")
    public Integer getStyleNum() {
        return styleNum;
    }

    public void setStyleNum(Integer styleNum) {
        this.styleNum = styleNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivStyleEntity that = (DivStyleEntity) o;
        return styleKey == that.styleKey && Objects.equals(styleName, that.styleName) && Objects.equals(styleNum, that.styleNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(styleKey, styleName, styleNum);
    }
}
