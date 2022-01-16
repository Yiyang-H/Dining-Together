package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;

import java.util.List;

public abstract class ApplicationService {

<<<<<<< HEAD
    public abstract Application approve(Integer applicationId, Integer foodId);
=======
    public abstract Application save(Application application);

    public abstract Application approve(Integer foodId, Integer candidateId);
>>>>>>> Lulu

    public abstract Application reject(Integer candidateId, Integer foodId);

    public abstract List<User> getAllCandidates(Integer foodId);
<<<<<<< HEAD


=======
>>>>>>> Lulu
}
