package com.summerHack.diningTogether.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "FOOD")
@Data
public class Food implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_id")
    private Integer foodId;

    // We do not use composite PK here because it is hard to reference the id in application
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "provider_id")
    private User provider;

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

    @Column(name = "consumer_number", nullable = false)
    private Integer consumerNumber;
}
