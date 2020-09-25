package pl.michalski.PortfolioProject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.michalski.PortfolioProject.Entities.Authority;
import pl.michalski.PortfolioProject.Entities.User;
import pl.michalski.PortfolioProject.dto.UserRegistrationDto;
import pl.michalski.PortfolioProject.repositories.AuthorityRepository;
import pl.michalski.PortfolioProject.repositories.UserRepository;

import java.util.HashSet;

@Component
public class UserRegistrationService {
    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;
    @Autowired
    public UserRegistrationService(AuthorityRepository authorityRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Boolean checkIfUserExists(String username, String email){
       if(userRepository.findByUsername(username).isEmpty() &&
       userRepository.findByEmail(email).isEmpty()){
           return true;
       }else {
           return false;
       }

    }

    public void save(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        Authority userRole = authorityRepository.save(Authority.builder().role("ROLE_USER").build());
        HashSet<Authority> authorities = new HashSet<>();
        authorities.add(userRole);
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setAuthorities(authorities);
        userRepository.save(user);
    }

    private PasswordEncoder passwordEncoder;
}
