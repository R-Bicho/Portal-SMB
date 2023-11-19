package com.portalSMB.portalSMB.model.service;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class PasswordService {
    private static final Random rand = new Random();
    private static final char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public PasswordService(){}

    public StringBuilder NewPassword()
    {
        StringBuilder newPassword = new StringBuilder();
        while (newPassword.length() < 6)
        {
            char ch = (char)(rand.nextInt('Z'-'A'+1)+'A');
            newPassword.append(ch);
        }
        newPassword.append('@');
        String number = String.valueOf(rand.nextInt(0,10));
        newPassword.append(number);

        return newPassword;
    }


}
