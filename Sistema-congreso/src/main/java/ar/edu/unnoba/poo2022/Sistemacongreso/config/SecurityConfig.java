package ar.edu.unnoba.poo2022.Sistemacongreso.config;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@Order(2)
@EnableWebSecurity
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
                .antMatchers("/webjars/**", "/resources/**","/css/**").permitAll()
                .antMatchers("/", "/home","/register").permitAll()
                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/usuarios/eventos")
                .permitAll()
                )
                .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .permitAll()
            );
       
                
        return http.build(); 
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return this.userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}

