package com.jgarivera.qwest.shared.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Duration;

@Configuration
class SecurityConfiguration {

    @Value("${spring.security.remember-me-key}")
    String rememberMeKey;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                c -> c.requestMatchers("/challenges/**").authenticated()
                        .anyRequest().permitAll()
        );

        http.formLogin(
                c -> c.loginPage("/login")
                        .failureUrl("/login-error")
        );

        http.rememberMe(c -> c.key(rememberMeKey)
                .tokenValiditySeconds((int) Duration.ofHours(24).toSeconds()));

        http.logout(c -> c.logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
