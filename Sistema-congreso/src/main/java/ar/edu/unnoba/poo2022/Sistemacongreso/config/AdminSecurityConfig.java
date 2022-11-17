package ar.edu.unnoba.poo2022.Sistemacongreso.config;

import ar.edu.unnoba.poo2022.Sistemacongreso.service.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class AdminSecurityConfig {
    private AdminServiceImp userDetailsService;

    @Autowired
    public AdminSecurityConfig(AdminServiceImp userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return getUserDetailsService();
    }

    private UserDetailsService getUserDetailsService() {
        return null;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
        http
                .antMatcher("/administrador/**")
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().hasAuthority("ROLE_ADMIN")
                )
                .formLogin((form) -> form
                        .loginPage("/administrador/login")
                        .usernameParameter("email")
                        .loginProcessingUrl("/administrador/login")
                        .defaultSuccessUrl("/administrador/home")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/administrador/logout")
                        .logoutSuccessUrl("/admin/login")
                        .permitAll()
                );
        return http.build();
    }

    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(userDetailsService);
                authProvider.setPasswordEncoder(passwordEncoder());
                return authProvider;
    }
}


