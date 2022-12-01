package ar.edu.unnoba.poo2022.Sistemacongreso.config;

import ar.edu.unnoba.poo2022.Sistemacongreso.service.EventServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(3)
@EnableWebSecurity
public class EventConfig {

    private EventServiceImp userDetailsService;

    @Autowired
    public EventConfig(EventServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests((Requests) -> Requests
                                .antMatchers("/webjars/**", "/resources/**", "/css/**").permitAll()
                                .antMatchers("/","/home","/eventos/**").permitAll()
                );
        return http.build();
}

    @Bean
    public UserDetailsService userDetailsService() {
        return this.userDetailsService;
    }
}

