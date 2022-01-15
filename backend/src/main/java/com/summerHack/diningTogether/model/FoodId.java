package com.summerHack.diningTogether.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class FoodId implements Serializable {
    @GeneratedValue
    @Column(name = "food_id")
    private Integer foodId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "provider_id")
    private User provider;
}
