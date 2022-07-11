package com.summerHack.diningTogether.utils;

import com.summerHack.diningTogether.exceptions.UserCodeNotFoundException;
import com.summerHack.diningTogether.model.User;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;


public interface EmailUtils {
    void sendEmail(User user, String siteUrl) throws MessagingException, UserCodeNotFoundException;
}
