package com.example.unza_library.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableScheduling
public class SecurityConfig {

    @Autowired
    private CustomLoginSuccessHandler successHandler;
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/registration/**","/css/**","/js/**").permitAll()
                        .requestMatchers("/login/**","/","/confirm","/confirmPage").permitAll()
                        .requestMatchers("/user/**","/book/**").hasAnyRole("USER")
                        .requestMatchers("/reception/**").hasAnyRole("RECEPTION")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER")
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(successHandler)
                        .permitAll()
                )
                .logout((log)->
                        log.logoutUrl("/logout")
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/")
                                .permitAll()
                )
                .rememberMe(Customizer.withDefaults())
        ;
        return http.build();
    }
}