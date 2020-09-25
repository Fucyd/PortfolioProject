package pl.michalski.PortfolioProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.michalski.PortfolioProject.dto.UserRegistrationDto;
import pl.michalski.PortfolioProject.service.UserRegistrationService;

import javax.validation.Valid;

@Controller
public class MainController {

    private UserRegistrationService userRegistrationService;
    @Autowired
    public MainController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping("")
    public String root() {
        return "main_page";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/main_page";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("newUser", userRegistrationDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("newUser") @Valid UserRegistrationDto userRegistrationDto,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if(!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())){
            bindingResult.rejectValue("password", "differentPasswords", "Passwords must match");
            return "registration";

        }
        if(!userRegistrationService.checkIfUserExists(userRegistrationDto.getUsername(), userRegistrationDto.getEmail())){
            bindingResult.rejectValue("username", "usernameNotAvailable");
            bindingResult.rejectValue("email", "emailNotAvailable");
            return "registration";

        }

        userRegistrationService.save(userRegistrationDto);
        return "redirect:/login";
    }
}
