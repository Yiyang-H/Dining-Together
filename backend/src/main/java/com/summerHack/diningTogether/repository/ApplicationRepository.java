package com.summerHack.diningTogether.repository;

import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.ApplicationId;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ApplicationRepository extends Repository<Application, ApplicationId> {
    Application getById(ApplicationId id);

    List<Application> findByIdConsumerId(Long id);
}
