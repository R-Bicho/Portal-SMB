package com.portalSMB.portalSMB.model.service;

import com.portalSMB.portalSMB.model.DTO.UserRole;
import com.portalSMB.portalSMB.model.entities.Person;
import com.portalSMB.portalSMB.model.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll()
    {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id)
    {
        return personRepository.findById(id);
    }

    public UserDetails findByLogin(String registration)
    {
        return personRepository.findByLogin(registration);
    }

    public UserDetails findByEmail(String email)
    {
        return personRepository.findByEmail(email);
    }

    public Person insert(Person obj)
    {
        return personRepository.save(obj);
    }

    public Person update(String registration, Person obj)
    {
        Person person = (Person) findByLogin(registration);
        updateData(person, obj);
        return personRepository.save(person);
    }

    private void updateData(Person person, Person obj)
    {
        person.setLogin(obj.getLogin());
        person.setPassword(obj.getPassword());
        person.setEmail(obj.getEmail());
        person.setRole(UserRole.USER);
    }





}
