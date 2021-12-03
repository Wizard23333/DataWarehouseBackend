package com.datawarehousebackend.mysql.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "div_review", schema = "dataWarehouseDB", catalog = "")
public class DivReviewEntity {
    private String reviewId;
    private String reviewerName;
    private Short helpfulNum;
    private byte score;
    private Double sentiment;
    private String date;
    private String summary;
    private String text;

    @Id
    @Column(name = "review_id")
    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    @Basic
    @Column(name = "reviewer_name")
    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    @Basic
    @Column(name = "helpful_num")
    public Short getHelpfulNum() {
        return helpfulNum;
    }

    public void setHelpfulNum(Short helpfulNum) {
        this.helpfulNum = helpfulNum;
    }

    @Basic
    @Column(name = "score")
    public byte getScore() {
        return score;
    }

    public void setScore(byte score) {
        this.score = score;
    }

    @Basic
    @Column(name = "sentiment")
    public Double getSentiment() {
        return sentiment;
    }

    public void setSentiment(Double sentiment) {
        this.sentiment = sentiment;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivReviewEntity that = (DivReviewEntity) o;
        return score == that.score && Objects.equals(reviewId, that.reviewId) && Objects.equals(reviewerName, that.reviewerName) && Objects.equals(helpfulNum, that.helpfulNum) && Objects.equals(sentiment, that.sentiment) && Objects.equals(date, that.date) && Objects.equals(summary, that.summary) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, reviewerName, helpfulNum, score, sentiment, date, summary, text);
    }
}
