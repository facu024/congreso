package ar.edu.unnoba.poo2022.Sistemacongreso.config;

import ar.edu.unnoba.poo2022.Sistemacongreso.service.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {
    private AdminServiceImp userDetailsService;

    @Autowired
    public AdminSecurityConfig(AdminServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admins/**")
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests((request) -> request
                        .anyRequest().hasAuthority("ROLE_ADMIN")
                )
                .formLogin((form) -> form
                        .loginPage("/admins/login")
                        .usernameParameter("email")
                        .loginProcessingUrl("/admins/login")
                        .defaultSuccessUrl("/admins/home")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/admins/logout")
                        .logoutSuccessUrl("/admins/login")
                        .permitAll()
                );
        return http.build();
    }
}


