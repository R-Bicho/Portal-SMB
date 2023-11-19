package com.portalSMB.portalSMB.view;

import com.portalSMB.portalSMB.controller.security.TokenService;
import com.portalSMB.portalSMB.model.entities.Coverage;
import com.portalSMB.portalSMB.model.entities.Person;
import com.portalSMB.portalSMB.model.entities.Voice;
import com.portalSMB.portalSMB.model.service.GenericFailureService;
import com.portalSMB.portalSMB.model.service.MailService;
import com.portalSMB.portalSMB.model.service.PersonService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Login {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MailService mailService;
    @Autowired
    private PersonService personService;
    @Autowired
    private GenericFailureService genericFailureService;
    List<Voice> list = new ArrayList<>();
    List<Coverage> coverageList = new ArrayList<>();
    private final Person newPerson = new Person();

    @GetMapping("/")
    public String loginAcesso()
    {
        return "login";
    }

    @PostMapping(path = "/login")
    public String validateLogin(Person person){
        try {
            newPerson.setLogin(person.getLogin());
            newPerson.setPassword(person.getPassword());
            var usernamePassword = new UsernamePasswordAuthenticationToken(newPerson.getLogin(), newPerson.getPassword());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Person) auth.getPrincipal());

            Person p1 = (Person) personService.findByLogin(person.getLogin());
            try {
                mailService.MailWithAttachment(p1.getEmail(), token, "Token - Portal SMB");
            }
            catch (MessagingException e)
            {
                return e.getMessage();
            }
            return "redirect:/token";
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return "redirect:/revisarValores";
        }
    }
    @GetMapping("/revisarValores")
    public String revisarValores(Model model)
    {
        model.addAttribute("erroLogin", "Senha ou Usuario incorreto");
        return "login";
    }

    @GetMapping("/token")
    public String token(Model model)
    {
        model.addAttribute("matricula", newPerson.getLogin());
        return "token";
    }

    @PostMapping("/token-enviado")
    public String newtoken(Person person, Model model){
        tokenService.validateToken(person.getToken());
        model.addAttribute("matricula", newPerson.getLogin());
        return "index";
    }

    @GetMapping("/home")
    public String index(Model model)
    {
        model.addAttribute("matricula", newPerson.getLogin());
        return "index";
    }

    @PostMapping("/create_dados")
    public String createDados(Coverage failure)
    {
        Person person = (Person) personService.findByLogin(newPerson.getLogin());
        Coverage coverage = new Coverage(null,
                failure.getOriginNumber(),
                failure.getMessageFailure(),
                failure.getRoaming(),
                person,
                failure.getAddress(),
                failure.getChipTesting(),
                failure.getRestarted(),
                failure.getDateFailure(),
                failure.getFailureHour());
        coverageList.add(coverage);
        genericFailureService.insert(coverage);
        return "redirect:/coverage";
    }

    @GetMapping("/coverage")
    public String returCoverage(Model model)
    {
        Coverage coverage = coverageList.get(0);
        model.addAttribute("matricula", newPerson.getLogin());
        model.addAttribute("numeroOrigem", coverage.getOriginNumber());
        model.addAttribute("mensagemFalha", coverage.getMessageFailure());
        model.addAttribute("roaming", coverage.getRoaming());
        model.addAttribute("endereco", coverage.getAddress());
        model.addAttribute("testChip", coverage.getChipTesting());
        model.addAttribute("reiniciou", coverage.getRestarted());
        model.addAttribute("data", coverage.getDateFailure());
        model.addAttribute("hora", coverage.getFailureHour());
        return "coverage";
    }

    @PostMapping("/create")
    public String create(Voice failure)
    {
        Person person = (Person) personService.findByLogin(newPerson.getLogin());
        list.clear();
        Voice voice = new Voice(null,
                failure.getOriginNumber(),
                failure.getMessageFailure(),
                failure.getRoaming(),
                person,
                failure.getTypeFailure(),
                failure.getDestinationNumber());
        list.add(voice);
        genericFailureService.insert(voice);
        return "redirect:/voice";
    }

    @GetMapping("/voice")
    public String returnVoice(Model model)
    {
        Voice voice = list.get(0);
        model.addAttribute("matricula", newPerson.getLogin());
        model.addAttribute("tipoFalha", voice.getTypeFailure());
        model.addAttribute("numeroOrigem", voice.getOriginNumber());
        model.addAttribute("numeroDestino", voice.getDestinationNumber());
        model.addAttribute("mensagemFalha", voice.getMessageFailure());
        model.addAttribute("roaming", voice.getRoaming());
        return "voice";
    }

}
