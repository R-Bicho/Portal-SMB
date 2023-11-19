package com.portalSMB.portalSMB.model.repositories;

import com.portalSMB.portalSMB.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface PersonRepository extends JpaRepository<Person, Long> {
    UserDetails findByLogin(String registration);
    UserDetails findByEmail(String email);

}
