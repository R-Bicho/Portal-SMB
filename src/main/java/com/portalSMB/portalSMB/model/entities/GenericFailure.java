package com.portalSMB.portalSMB.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.portalSMB.portalSMB.controller.restApi.deserialize.AccountDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_failure")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type_Error")
@JsonDeserialize(using = AccountDeserialize.class)
public abstract class GenericFailure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 110, message = "Numero de origem deve conter 10 ou 11 digitos")
    private String originNumber;
    @NotNull
    @Size(max = 200)
    private String messageFailure;
    @NotNull
    @Size(max = 3, message = "Escolha sim ou não")
    private String roaming;
    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;
    public GenericFailure(){}
    public GenericFailure(Long id, String originNumber, String messageFailure,String roaming ,Person person) {
        this.id = id;
        this.originNumber = originNumber;
        this.messageFailure = messageFailure;
        this.roaming = roaming;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginNumber() {
        return originNumber;
    }

    public void setOriginNumber(String originNumber) {
        this.originNumber = originNumber;
    }

    public String getMessageFailure() {
        return messageFailure;
    }

    public void setMessageFailure(String messageFailure) {
        this.messageFailure = messageFailure;
    }

    public String getRoaming() {
        return roaming;
    }

    public void setRoaming(String roaming) {
        this.roaming = roaming;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenericFailure that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
