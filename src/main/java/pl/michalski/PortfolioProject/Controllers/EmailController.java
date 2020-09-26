package pl.michalski.PortfolioProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.michalski.PortfolioProject.service.EmailService;

@Controller
public class EmailController {
    private final EmailService emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailController(EmailService emailSender,
                           TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @RequestMapping("/mail")
    public String send() {
        Context context = new Context();
        context.setVariable("header", "Nowy artykuł na CodeCouple");
        context.setVariable("title", "#8 Spring Boot – email - szablon i wysyłanie");
        context.setVariable("description", "Tutaj jakis opis...");
        String body = templateEngine.process("template", context);
        emailSender.sendSimpleMessage("your.email.here@gmail.com", "CodeCouple Newsletter", body);
        return "index";
    }
}
