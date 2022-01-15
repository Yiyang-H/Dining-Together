package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;

import java.util.List;

public abstract class ApplicationService {
    public abstract Application save(Application application) ;

    public abstract Application approve(Integer foodId, Integer candidateId);

    public abstract Application reject(Integer foodId, Integer candidateId);

    public abstract List<User> getAllCandidates(Integer foodId);
}
