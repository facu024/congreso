package ar.edu.unnoba.poo2022.Sistemacongreso.config;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
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
                .authorizeHttpRequests()
                .antMatchers("/","/home").permitAll()
                .antMatchers("/webjars/**", "/resources/**", "/css/**").permitAll()
                .antMatchers("/usuarios/new").permitAll()
                .antMatchers(HttpMethod.POST,"/usuarios").permitAll()
                .anyRequest().authenticated()
                //.antMatchers("/").permitAll()
                //.anyRequest().hasAuthority("ROLE_USER")

                .and()
                .formLogin((form) -> form
                        .loginPage("/login").defaultSuccessUrl("/usuarios", true)
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll()
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

