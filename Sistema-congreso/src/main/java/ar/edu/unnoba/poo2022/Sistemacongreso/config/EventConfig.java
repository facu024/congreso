package ar.edu.unnoba.poo2022.Sistemacongreso.config;

import ar.edu.unnoba.poo2022.Sistemacongreso.service.EventServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class EventConfig {

    //private EventServiceImp userDetailsService;

    //@Autowired
    //public EventConfig(EventServiceImp userDetailsService) {
      //  this.userDetailsService = userDetailsService;
    //}

    //@Bean
    //public UserDetailsService userDetailsService(){return this.userDetailsService;}
}
