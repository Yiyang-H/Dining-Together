package com.summerHack.diningTogether.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class FoodId implements Serializable {
    @GeneratedValue
    @Column(name = "food_id")
    private Integer foodId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "provider_id")
    private User provider;


    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public User getProvider() {
        return provider;
    }

    public void setProvider(User provider) {
        this.provider = provider;
    }
}
