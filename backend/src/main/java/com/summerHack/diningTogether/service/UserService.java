package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.DTO.UserDTO;
import com.summerHack.diningTogether.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public abstract class UserService {
    public abstract UserDTO getProfile(int id);

    public abstract User update(int id, User user);

}
