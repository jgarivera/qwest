package com.jgarivera.qwest;

import com.jgarivera.qwest.auth.domain.User;
import com.jgarivera.qwest.auth.domain.UserRepository;
import com.jgarivera.qwest.auth.domain.Username;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                c -> c.requestMatchers("/challenges").authenticated()
                        .anyRequest().permitAll()
        );

        http.formLogin(
                c -> c.loginPage("/login")
        );

        http.logout(c -> c.logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(new Username(username));

            if (user == null) {
                throw new UsernameNotFoundException(String.format("User '%s' not found", username));
            } else {
                return user;
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
