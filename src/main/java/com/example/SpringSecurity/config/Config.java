package com.example.SpringSecurity.config;

import com.example.SpringSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class Config {
    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            var user = userRepository.findByEmail(username);
            if(user==null){
                throw new UsernameNotFoundException("USER NOT FOUND");
            }else return user;
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        var auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());

        http.csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(eh -> eh.accessDeniedPage(   "/forbidden"));

        http.formLogin(fl ->
                fl.loginProcessingUrl("/auth")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginPage("/sign-in")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/sign-in?error"));

        http.logout(lg -> lg.logoutUrl("/logout").logoutSuccessUrl("/"));

        return http.build();
    }
}