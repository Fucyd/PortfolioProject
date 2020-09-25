package pl.michalski.PortfolioProject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.michalski.PortfolioProject.Entities.Authority;
import pl.michalski.PortfolioProject.Entities.User;
import pl.michalski.PortfolioProject.repositories.AuthorityRepository;
import pl.michalski.PortfolioProject.repositories.UserRepository;

import java.util.HashSet;
@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void loadSecurityData() {
        Authority admin = authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
        Authority moderator = authorityRepository.save(Authority.builder().role("ROLE_MODERATOR").build());
        Authority user = authorityRepository.save(Authority.builder().role("ROLE_USER").build());

        HashSet<Authority> adminSet = new HashSet<>();
        adminSet.add(admin);

        HashSet<Authority> moderatorSet = new HashSet<>();
        adminSet.add(moderator);

        HashSet<Authority> userSet = new HashSet<>();
        adminSet.add(user);

        userRepository.save(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .authorities(adminSet)
                .build()
        );

        userRepository.save(User.builder()
                .username("moderator")
                .password(passwordEncoder.encode("123"))
                .authorities(moderatorSet)
                .build()
        );

        userRepository.save(User.builder()
                .username("user")
                .password(passwordEncoder.encode("123"))
                .authorities(userSet)
                .build()
        );
    }

    @Override
    public void run(String... args) throws Exception {
        if (authorityRepository.count() == 0) {
            loadSecurityData();
        }
    }


}
