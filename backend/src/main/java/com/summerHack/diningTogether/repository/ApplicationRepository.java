package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByFoodIdAndCandidateId(Long foodId, Long candidateId);

    boolean existByFoodIdAndCandidateId(Long foodId, Long candidateId);

    List<Application> findAllByFoodId(Long foodId);
}
