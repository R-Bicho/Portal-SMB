package com.portalSMB.portalSMB.view;

import com.portalSMB.portalSMB.model.entities.Person;
import com.portalSMB.portalSMB.model.service.PasswordService;
import com.portalSMB.portalSMB.model.service.MailService;
import com.portalSMB.portalSMB.model.service.PersonService;
import com.portalSMB.portalSMB.model.service.ValidationPassword;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Mail {
    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private PersonService personService;
    protected List<String> stringList = new ArrayList<>();

    @GetMapping("/email")
    public String email()
    {
        return "alterarSenha";
    }

    @PostMapping("/escrever-email")
    public String alterarSenha(Person person) {

        String actualPassword = passwordService.NewPassword().toString();
        stringList.add(actualPassword);
        String email = person.getEmail();
        stringList.add(email);
        try {
            mailService.MailWithAttachment(email, actualPassword, "Reset senha - Portal SMB");
        }
        catch (MessagingException e)
        {
            return e.getMessage();
        }
        return "redirect:/alterar-senha";
    }

    @GetMapping("/alterar-senha")
    public String senha()
    {
        return "novaSenha";
    }

    @PostMapping("/nova-senha")
    public String newPassword(ValidationPassword password){

        String emailPassword = stringList.get(0);
        String email = stringList.get(1);

        if (emailPassword.equals(password.getOldPassword()))
        {
            String encryptedPassword = new BCryptPasswordEncoder().encode(password.getPassword());
            Person p = (Person) personService.findByEmail(email);
            p.setPassword(encryptedPassword);
            personService.update(p.getLogin(), p);
            return "redirect:/";
        }
        return "redirect:/verificar-senha";
    }

    @GetMapping("/verificar-senha")
    public String verificarSenha(Model model)
    {
        model.addAttribute("senhaIncorreta", "Senha temporária deve ser igual a do email");
        return "novaSenha";
    }

}
