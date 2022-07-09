package com.summerHack.diningTogether.utils;

import org.springframework.stereotype.Component;


public interface EmailUtils {
    void sendEmail(String toAddress, String subject, String body);
}
