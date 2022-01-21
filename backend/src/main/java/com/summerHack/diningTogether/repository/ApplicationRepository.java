package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.ApplicationStatus;
import com.summerHack.diningTogether.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByFoodIdAndCandidateId(Long foodId, Long candidateId);

    boolean existsByFoodIdAndCandidateId(Long foodId, Long candidateId);

    List<Application> findAllByFoodId(Long foodId);

    Optional<Application> findByStatus(@Nullable ApplicationStatus status);

    long countByCandidateAndStatus(@NonNull User candidate, @NonNull ApplicationStatus status);

    List<Application> findByCandidate(@NonNull User candidate);


}
