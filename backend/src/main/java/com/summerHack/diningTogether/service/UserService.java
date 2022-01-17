package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.DTO.UserDTO;
import com.summerHack.diningTogether.model.User;

public abstract class UserService {
    public abstract UserDTO getProfile(long id);

    public abstract User update(long id, User user);

}
