package com.banksimulation.config;

import com.banksimulation.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {

    private final SecurityService securityService;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
        return
                http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/accounts/**").hasAuthority("Admin")
                                .requestMatchers("/transaction/**").hasAnyAuthority("Admin","Cashier")
                                .requestMatchers("/","/login").permitAll()
                                .anyRequest().authenticated())
                 .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .successHandler(authSuccessHandler)
                                .failureUrl("/login?error=true")
                                .permitAll())
                .logout(logout ->
                                logout
                                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                        .logoutSuccessUrl("/login"))
                .rememberMe(rememberMe ->
                        rememberMe
                                .tokenValiditySeconds(300)
                                .key("bankapp")
                                .userDetailsService(securityService))
                .build();

    }
}
