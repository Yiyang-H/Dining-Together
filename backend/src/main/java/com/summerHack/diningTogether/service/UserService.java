package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.dto.UserDTO;
import com.summerHack.diningTogether.model.User;

public abstract class UserService {
    public abstract UserDTO getProfile(int id);

    public abstract User update(int id, User user);

}
