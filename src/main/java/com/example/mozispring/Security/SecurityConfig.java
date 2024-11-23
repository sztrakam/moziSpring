package com.example.mozispring.Security;

import com.example.mozispring.Model.MyAppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    private final MyAppUserService appUserService;

    public SecurityConfig(@Lazy MyAppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/csrf","/api/**","/contact","/login", "/h2-console/**", "/static/**", "/css/**", "/js/**", "/images/**", "/register").permitAll();
                    registry.anyRequest().authenticated();
                })
                .headers(headers -> headers.frameOptions().sameOrigin())
                .formLogin(httpForm -> {
                    httpForm
                            .loginPage("/login")
                            .usernameParameter("felhasznalonev")
                            .passwordParameter("jelszo")
                            .permitAll()
                            .defaultSuccessUrl("/index", true)
                            .failureUrl("/login?error=true")
                            .successHandler((request, response, authentication) -> {
                                System.out.println("Bejelentkezés sikeres: " + authentication.getName());
                                response.sendRedirect("/index");
                            })
                            .failureHandler((request, response, exception) -> {
                                System.out.println("Bejelentkezési hiba: " + exception.getMessage());
                                response.sendRedirect("/login?error=true");
                            });
                })
                .logout(logout -> {
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/login");
                });

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return appUserService;
    }
}
