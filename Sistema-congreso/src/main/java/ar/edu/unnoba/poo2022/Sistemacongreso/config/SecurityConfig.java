package ar.edu.unnoba.poo2022.Sistemacongreso.config;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
                        //la primer linea permite contenido estatico (estilos)
                        .antMatchers("/webjars/**", "/resources/**", "/resources/static/**").permitAll()
                        //  tanto  "/"  como  "/usuarios/new  son la raiz de la app, esta permitida para cualquier
                        //usuario independientemente de si se logueo o no
                        .antMatchers("/","/usuarios/new").permitAll()
                        //para /usuarios mientras vaya por post va a ser permitido para todos
                        //si barra user se mostraria por get, va a listar todos los usuarios del sistema (falla de seguridad)
                        .antMatchers(HttpMethod.POST,"/usuarios").permitAll()
                        .anyRequest().hasAuthority("ROLE_USER")
                        //anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    public UserDetailsService userDetailsService(){
        return userDetailsService();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


}

