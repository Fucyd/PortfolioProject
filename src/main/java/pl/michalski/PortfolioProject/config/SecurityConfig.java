package pl.michalski.PortfolioProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> {
                    authorize
                            .antMatchers("/resources/**", "/static/**", "/css/**", "/images/**").permitAll()
                            .antMatchers("/registration", "/products/all", "/h2/**",
                                    "/").permitAll()
                            .antMatchers("/products/add", "/products/save").hasRole("ADMIN");

                })
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return SfgPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Autowired
//    JpaUserDetailsService jpaUserDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.jpaUserDetailsService).passwordEncoder(passwordEncoder());
//
//    }


        //        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}123")
//                .roles("ADMIN")
//                .and()
//                .withUser("user")
//                .password("{noop}123")
//                .roles("USER");
//    }
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("piotr")
//                .password("{noop}123")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("{noop}123")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
