package ar.edu.unnoba.poo2022.Sistemacongreso.config;

import ar.edu.unnoba.poo2022.Sistemacongreso.service.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@Order(1)
@EnableWebSecurity
public class AdminSecurityConfig {
    private AdminServiceImp userDetailsService;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public AdminSecurityConfig(AdminServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityAdminFilterChain(HttpSecurity http) throws Exception {
        http
        
        .antMatcher("/admins/**")
        .userDetailsService(userDetailsService)
        .authorizeHttpRequests((requests) -> requests
            .anyRequest().hasAuthority("ROLE_ADMIN")
        )
        .formLogin((form) -> form
            .loginPage("/admins/login")
            .usernameParameter("email")
            .loginProcessingUrl("/admins/login")
            .defaultSuccessUrl("/admins")
            .permitAll()
        )
        .logout((logout) -> logout
            .logoutUrl("/admins/logout")
            .logoutSuccessUrl("/home")
            .permitAll()
        );
   

        return http.build();
    }
}        


