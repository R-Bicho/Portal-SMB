package com.portalSMB.portalSMB.model.service;

import org.springframework.stereotype.Service;

@Service
public class ValidationPassword {
    private String password;
    private String oldPassword;

    public ValidationPassword(){}

    public ValidationPassword(String password, String oldPassword) {
        this.password = password;
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
