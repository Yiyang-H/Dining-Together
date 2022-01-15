package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.model.Profile;
import com.summerHack.diningTogether.model.User;

public abstract class UserService {
    public abstract Profile getProfile(int id);

    public abstract User update(int id);
}
