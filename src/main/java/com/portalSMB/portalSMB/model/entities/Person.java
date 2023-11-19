package com.portalSMB.portalSMB.model.entities;

import com.portalSMB.portalSMB.model.DTO.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_person")
public class Person implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private final Date date = new Date();
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @Transient
    private String token;
    private UserRole role;
    @OneToMany(mappedBy = "person")
    private List<GenericFailure> genericFailures;

    public Person(){}
    public Person(String login, String password)
    {
        this.login = login;
        this.password = password;
    }
    public Person(Long id, String login, String email, String password, String token,UserRole role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.token = token;
        this.role = role;
    }

    public Person(String login, String email, String password, UserRole role) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public List<GenericFailure> getGenericFailures() {
        return genericFailures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(getLogin(), person.getLogin());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin());
    }


    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
