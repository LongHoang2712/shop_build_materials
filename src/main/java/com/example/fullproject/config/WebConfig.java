package com.example.fullproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebConfig {
    @Autowired
    public UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) -> request
                        .requestMatchers("/register", "/login", "/404", "/homepage/**", "/product", "/detail/**", "/category",
                                "/manufacturer", "/btrap/**", "/assets/**", "/imgs/**","/logout","/search_homepage",
                                "/productsearch","/categorysearch","/manufacturersearch","/add/**","demo"
                                )
                        .permitAll()
                        .requestMatchers("/employee/**").hasAuthority("ADMIN")
                        .requestMatchers("/detail-bill-customer/**").hasAnyAuthority("USER","ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form.loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .successHandler(((request, response, authentication) -> {
                            for (GrantedAuthority authority : authentication.getAuthorities()) {
                                if (authority.getAuthority().equals("ADMIN")) {
                                    response.sendRedirect("./employee/listproduct");
                                } else if (authority.getAuthority().equals("USER")) {
                                    response.sendRedirect("/homepage");
                                }
                            }
                        }))
                ).logout(
                        logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                )
                .exceptionHandling()
                .accessDeniedHandler((req, res, e) -> {
                    res.sendRedirect("/404");
                })
                .accessDeniedPage("/404");
        return http.build();
    }

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
