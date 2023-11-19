package com.portalSMB.portalSMB.model.service;

import com.portalSMB.portalSMB.model.entities.GenericFailure;
import com.portalSMB.portalSMB.model.repositories.GenericFailureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GenericFailureService {
    @Autowired
    private GenericFailureRepository genericFailureRepository;

    public List<GenericFailure> findAll()
    {
        return genericFailureRepository.findAll();
    }

    public Optional<GenericFailure> findById(Long id)
    {
        return genericFailureRepository.findById(id);
    }

    public GenericFailure insert(GenericFailure obj)
    {
        return genericFailureRepository.save(obj);
    }

}
