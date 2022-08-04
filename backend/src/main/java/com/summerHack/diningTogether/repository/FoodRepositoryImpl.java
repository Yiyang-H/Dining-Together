package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Category;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.utils.MapUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FoodRepositoryImpl implements FoodRepositoryCustom {

    final EntityManager em;
    MapUtils mapUtils;
    @Override
    public List<Food> findByParameters(Optional<Category> category,
                                       Optional<Boolean> confirmed,
                                       Optional<Long> distance,
                                       User user) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Food> query = cb.createQuery(Food.class);

        final Root<Food> food = query.from(Food.class);
        final ArrayList<Predicate> predicates = new ArrayList<>();

        category.ifPresent(c -> predicates.add(cb.equal(food.get("category"), c)));
        confirmed.ifPresent(c -> predicates.add(cb.equal(food.get("completed"), c)));
        distance.ifPresent(d->{
            ;
            predicates
                    .add(cb
                            .le(mapUtils
                                    .getDistance(user.getAddress(),
                                            food.get("location"))))
        });
        query.where(predicates.toArray(Predicate[]::new));
        if (distance.isPresent() == false)
            return em.createQuery(query).getResultList();
    }
}
