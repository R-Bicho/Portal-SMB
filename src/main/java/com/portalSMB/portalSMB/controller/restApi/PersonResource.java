package com.portalSMB.portalSMB.controller.restApi;

import com.portalSMB.portalSMB.model.entities.Person;
import com.portalSMB.portalSMB.model.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonResource {
    @Autowired
    private PersonService personService;
    @GetMapping
    public ResponseEntity<List<Person>> findAll()
    {
        List<Person> personList = personService.findAll();
        return ResponseEntity.ok().body(personList);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Person>> findById(
            @PathVariable(value = "id") Long id)
    {
        Optional<Person> person = personService.findById(id);
        return ResponseEntity.ok().body(person);
    }

}
