package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;
import ar.edu.unnoba.poo2022.Sistemacongreso.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EventServiceImp implements IEventService, UserDetailsService {

    @Autowired
    private EventoRepository repository;

    @Override
    public Evento create(Evento evento) {
        if(repository.findByNombre(evento.getNombre())==null){
            evento.setDescripcion(evento.getDescripcion());
            evento = repository.save(evento);
        }
        return evento ;
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        return (UserDetails)repository.findByNombre(nombre);
    }
}
