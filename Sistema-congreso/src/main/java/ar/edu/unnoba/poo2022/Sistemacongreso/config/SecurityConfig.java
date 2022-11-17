package ar.edu.unnoba.poo2022.Sistemacongreso.config;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class SecurityConfig {

    private UserServiceImp userDetailsService;

    @Autowired
    public SecurityConfig(UserServiceImp userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/usuarios/new").permitAll()
                        .antMatchers("/webjars/**", "/resources/**", "/static/**").permitAll()

                        .antMatchers(HttpMethod.POST,"/usuarios").permitAll()
                        //.anyRequest().hasAuthority("ROLE_USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }
}

