package com.datawarehousebackend.mysql.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "div_time", schema = "dataWarehouseDB", catalog = "")
public class DivTimeEntity {
    private int timeKey;
    private short year;
    private Byte month;
    private Byte day;
    private Byte quarter;
    private Byte weekday;


    @Id
    @Column(name = "time_key")
    public int getTimeKey() {
        return timeKey;
    }

    public void setTimeKey(int timeKey) {
        this.timeKey = timeKey;
    }

    @Basic
    @Column(name = "year")
    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    @Basic
    @Column(name = "month")
    public Byte getMonth() {
        return month;
    }

    public void setMonth(Byte month) {
        this.month = month;
    }

    @Basic
    @Column(name = "day")
    public Byte getDay() {
        return day;
    }

    public void setDay(Byte day) {
        this.day = day;
    }

    @Basic
    @Column(name = "quarter")
    public Byte getQuarter() {
        return quarter;
    }

    public void setQuarter(Byte quarter) {
        this.quarter = quarter;
    }

    @Basic
    @Column(name = "weekday")
    public Byte getWeekday() {
        return weekday;
    }

    public void setWeekday(Byte weekday) {
        this.weekday = weekday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivTimeEntity that = (DivTimeEntity) o;
        return timeKey == that.timeKey && year == that.year && Objects.equals(month, that.month) && Objects.equals(day, that.day) && Objects.equals(quarter, that.quarter) && Objects.equals(weekday, that.weekday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeKey, year, month, day, quarter, weekday);
    }


}
