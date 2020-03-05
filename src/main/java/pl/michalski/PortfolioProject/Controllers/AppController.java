package pl.michalski.PortfolioProject.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @RequestMapping("/")
    public String mainPage(){
        return "mainPage";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/admin")
    public String afterLogin(){
        return "adminPanel";
    }


}
