package com.summerHack.diningTogether.model;

import javax.persistence.Lob;
import java.sql.Timestamp;
import java.util.List;

public class FoodBrief {

    private User provider;
    private String title;

    private String description;

    private Category category;

    @Lob
    private byte[] picture;

    private Timestamp createdTime;

    private String location;

    private FoodType foodType;

    private Boolean completed;

    private List<Application> applications;

    private Integer consumerNumber;
}
