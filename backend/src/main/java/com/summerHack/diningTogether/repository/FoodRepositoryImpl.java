package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Category;
import com.summerHack.diningTogether.model.Food;
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

    @Override
    public List<Food> findByParameters(
        Optional<Category> category, Optional<Boolean> confirmed) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Food> query = cb.createQuery(Food.class);

        final Root<Food> food = query.from(Food.class);
        final ArrayList<Predicate> predicates = new ArrayList<>();

        category.ifPresent(c -> predicates.add(cb.equal(food.get("category"), c)));
        confirmed.ifPresent(c -> predicates.add(cb.equal(food.get("confirmed"), c)));

        query.where(predicates.toArray(Predicate[]::new));
        return em.createQuery(query).getResultList();
    }
}
