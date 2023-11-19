package com.portalSMB.portalSMB.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "tb_coverage")
public class Coverage extends GenericFailure implements Serializable {
    @NotNull
    @Size(min = 20, max = 80, message = "O endereço deve conter entre 20 e 80 caracteres")
    private String address;
    @NotNull
    private String chipTesting;
    @NotNull
    private String restarted;
    private String dateFailure;
    private String failureHour;
    public Coverage(){}
    public Coverage(Long id, String originNumber, String messageFailure, String roaming , Person person, String address, String chipTesting, String restarted, String dateFailure, String failureHour) {
        super(id, originNumber, messageFailure,roaming,person);
        this.address = address;
        this.chipTesting = chipTesting;
        this.restarted = restarted;
        this.dateFailure = dateFailure;
        this.failureHour = failureHour;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChipTesting() {
        return chipTesting;
    }

    public void setChipTesting(String chipTesting) {
        this.chipTesting = chipTesting;
    }

    public String getRestarted() {
        return restarted;
    }

    public void setRestarted(String restarted) {
        this.restarted = restarted;
    }

    public String getDateFailure() {
        return dateFailure;
    }

    public void setDateFailure(String dateFailure) {
        this.dateFailure = dateFailure;
    }

    public String getFailureHour() {
        return failureHour;
    }

    public void setFailureHour(String failureHour) {
        this.failureHour = failureHour;
    }
}
