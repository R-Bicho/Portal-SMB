package com.portalSMB.portalSMB.view;

import com.portalSMB.portalSMB.model.DTO.UserRole;
import com.portalSMB.portalSMB.model.entities.Person;
import com.portalSMB.portalSMB.model.repositories.PersonRepository;
import com.portalSMB.portalSMB.model.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Register {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    @GetMapping("/cadastrar")
    public String cadastrar()
    {
        return "cadastrar";
    }

    @PostMapping("/novo-cadastro")
    public String validateRegister(Person person)
    {
        if(this.personService.findByLogin(person.getLogin()) != null) return null;

        String encryptedPassword = new BCryptPasswordEncoder().encode(person.getPassword());
        Person newPerson = new Person(person.getLogin(), person.getEmail(), encryptedPassword, UserRole.USER);
        this.personService.insert(newPerson);
        return "redirect:/";
    }

}
