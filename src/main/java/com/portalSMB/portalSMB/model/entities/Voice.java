package com.portalSMB.portalSMB.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
@Entity
public class Voice extends GenericFailure implements Serializable {
    @NotNull
    private String typeFailure;
    @NotNull
    @Size(min = 3, max = 110, message = "Numero de destino deve conter entre 3 e 11 digitos")
    private String destinationNumber;
    public Voice(){}

    public Voice(Long id, String originNumber, String messageFailure, String roaming , Person person, String typeFailure, String destinationNumber) {
        super(id, originNumber, messageFailure,roaming,person);
        this.typeFailure = typeFailure;
        this.destinationNumber = destinationNumber;
    }
    public String getTypeFailure() {
        return typeFailure;
    }

    public void setTypeFailure(String typeFailure) {
        this.typeFailure = typeFailure;
    }

    public String getDestinationNumber() {
        return destinationNumber;
    }

    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

}


