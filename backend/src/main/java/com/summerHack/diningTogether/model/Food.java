package com.summerHack.diningTogether.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "FOOD")
public class Food implements Serializable {
    @EmbeddedId
    private FoodId id;

    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    // TODO: add category

    @Column(name = "picture")
    @Lob
    private byte[] picture;

    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;

    @Column(name = "created_time", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp createdTime;

    @Column(name = "location", length = 255, nullable = false)
    private String location;

    @Column(name = "food_type", nullable = false)
    private FoodType foodType;

    @Column(name = "completed", nullable = false)
    @ColumnDefault("FALSE")
    private Boolean completed;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.food")
    private List<Application> applications;


    public FoodId getId() {
        return id;
    }

    public void setId(FoodId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
