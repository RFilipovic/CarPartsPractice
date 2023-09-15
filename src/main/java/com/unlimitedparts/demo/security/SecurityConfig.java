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

        UserDetails sales = User.builder()
                .username("sales")
                .password("{noop}prodaja")
                .roles("SALES")
                .build();

        UserDetails warehouse = User.builder()
                .username("warehouse")
                .password("{noop}skladiste")
                .roles("WAREHOUSE")
                .build();

        return new InMemoryUserDetailsManager(sales, warehouse);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.POST, "/sale/**").hasRole("SALES")
                                .requestMatchers(HttpMethod.DELETE, "/sale/**").hasRole("SALES")
                                .requestMatchers(HttpMethod.PUT, "/sale/**").hasRole("SALES")
                                .requestMatchers(HttpMethod.GET, "/sale/**").hasRole("SALES")
                                .requestMatchers(HttpMethod.POST, "/warehouse/**").hasRole("WAREHOUSE")
                                .requestMatchers(HttpMethod.GET, "/warehouse/**").hasRole("WAREHOUSE")
                                .requestMatchers(HttpMethod.DELETE, "/warehouse/**").hasRole("WAREHOUSE")
                                .requestMatchers(HttpMethod.PUT, "/warehouse/**").hasRole("WAREHOUSE")
                );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}