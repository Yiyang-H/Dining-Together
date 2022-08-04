package com.summerHack.diningTogether.service;

import com.summerHack.diningTogether.exceptions.UserCodeNotFoundException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.UserCodeRepository;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;


public interface EmailService {

    void sendEmail(User user, String siteUrl) throws MessagingException, UserCodeNotFoundException;
}
