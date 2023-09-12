package com.unlimitedparts.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.builder()
                .username("sales")
                .password("{noop}prodaja")
                .roles("SALES")
                .build();

        UserDetails admin = User.builder()
                .username("warehouse")
                .password("{noop}skladiste")
                .roles("WAREHOUSE")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/car-part").hasRole("SALES")
                                .requestMatchers(HttpMethod.DELETE, "/car-part/**").hasRole("WAREHOUSE")
                                .requestMatchers(HttpMethod.POST, "/car-part").hasRole("WAREHOUSE")
                                .requestMatchers(HttpMethod.POST, "/sale/sales").hasRole("WAREHOUSE")
                                .requestMatchers(HttpMethod.POST, "/sale/products").hasRole("WAREHOUSE")
                );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}