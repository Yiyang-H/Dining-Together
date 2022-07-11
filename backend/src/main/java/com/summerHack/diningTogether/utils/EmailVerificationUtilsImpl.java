package com.summerHack.diningTogether.utils;

import com.summerHack.diningTogether.exceptions.FoodNotFoundException;
import com.summerHack.diningTogether.exceptions.UserCodeNotFoundException;
import com.summerHack.diningTogether.model.User;
import com.summerHack.diningTogether.repository.UserCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailVerificationUtilsImpl implements EmailUtils{
        @Autowired
        private UserCodeRepository userCodeRepository;
        @Autowired
        private JavaMailSender sender;

        @Override
        public void sendEmail(User user, String siteURL) throws MessagingException, UserCodeNotFoundException {

                String toAddress = user.getEmail();

                String subject = "Please verify your registration";
                String content = "Hi there,"
                        + "<br>Please click the link below to verify your registration:<br>"
                        + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                        + "Thank you,<br>"
                        + "dining together.co";

                MimeMessage message = sender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);


                try {
                        helper.setTo(toAddress);
                        helper.setSubject(subject);
                        helper.setText(content);
                } catch (MessagingException e) {
                        e.printStackTrace();
                }
                String userCode = userCodeRepository
                        .findById(user.getId())
                        .orElseThrow(UserCodeNotFoundException::new)
                        .getVerificationCode();
                String verifyURL = siteURL + "/api/v1/auth/verify?code=" + userCode;

                content = content.replace("[[URL]]", verifyURL);

                helper.setText(content);


                sender.send(message);
        }




}
