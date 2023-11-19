package com.portalSMB.portalSMB.controller.restApi;

import com.portalSMB.portalSMB.model.entities.GenericFailure;
import com.portalSMB.portalSMB.model.service.GenericFailureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/failure")
public class GenericFailureResouce {
    @Autowired
    private GenericFailureService genericFailureService;

    @GetMapping
    public ResponseEntity<List<GenericFailure>> findAll()
    {
        List<GenericFailure> genericFailures = genericFailureService.findAll();
        return ResponseEntity.ok().body(genericFailures);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<GenericFailure>> findById(
            @PathVariable(value = "id") Long id)
    {
        Optional<GenericFailure> genericFailure = genericFailureService.findById(id);
        return ResponseEntity.ok().body(genericFailure);
    }

}
