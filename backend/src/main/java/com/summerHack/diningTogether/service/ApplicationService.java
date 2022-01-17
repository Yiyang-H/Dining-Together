package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.exceptions.UnimplementedException;
import com.summerHack.diningTogether.model.Application;
import com.summerHack.diningTogether.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationService {

    public Application save(Application application) {
        throw new UnimplementedException();
    }

    public Application approve(Integer foodId, Integer candidateId) {
        throw new UnimplementedException();
    }

    public Application reject(Integer foodId, Integer candidateId) {
        throw new UnimplementedException();
    }

    public List<User> getAllCandidates(Integer foodId) {
        throw new UnimplementedException();
    }
}
