package pl.michalski.PortfolioProject.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails moderator = User.withDefaultPasswordEncoder()
                .username("Mod")
                .password("123")
                .roles("MODERATOR")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("User")
                .password("123")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("Admin")
                .password("123")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, moderator, admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers(HttpMethod.POST, "/api").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }


}
