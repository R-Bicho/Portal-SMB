/*
package com.portalSMB.portalSMB.controller.config;

import com.portalSMB.portalSMB.model.entities.Coverage;
import com.portalSMB.portalSMB.model.entities.GenericFailure;
import com.portalSMB.portalSMB.model.entities.Person;
import com.portalSMB.portalSMB.model.entities.Voice;
import com.portalSMB.portalSMB.model.repositories.CoverageRepository;
import com.portalSMB.portalSMB.model.repositories.GenericFailureRepository;
import com.portalSMB.portalSMB.model.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Date;

//@Configuration
//@Profile("test")
public class testConfig implements CommandLineRunner {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CoverageRepository coverageRepository;

    @Autowired
    private GenericFailureRepository genericFailureRepository;

    @Override
    public void run(String... args) throws Exception {
        Person p1 = new Person(null, "F123456", "rogerio@outlook.com", "123456789", null,  null);
        Person p2 = new Person(null,"F567889", "Leticia@outlook.com","123456789", null,null);
        personRepository.saveAll(Arrays.asList(p1, p2));

        GenericFailure v1 = new Voice(null, "11912345677", "Bloqueado", "nao",p1, "Não Recebe", "11912345999");
        GenericFailure v2 = new Voice(null, "11912345688", "Bloqueado", "nao",p2, "Não efetua", "11912345000");

        GenericFailure v3 = new Coverage(null, "11912345666", "sem mensagem", "nao",p1, "NaoTeInteressa 2000, São Paulo, Santo André, cep 01234560", "Sim", "Sim","10/10/2023", new Date().toString());
        GenericFailure v4 = new Coverage(null, "11912345555", "sem mensagem","nao", p1, "NaoTeInteressa 2000, São Paulo, Santo André, cep 01234560", "Sim", "Sim","10/10/2023", new Date().toString());
        genericFailureRepository.saveAll(Arrays.asList(v1, v2, v3, v4));

    }
}
*/
