package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;
import ar.edu.unnoba.poo2022.Sistemacongreso.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImp implements IEventService, UserDetailsService {

    @Autowired
    private EventoRepository repository;

    @Override
    public Evento create(Evento evento) {
        if(repository.findByNombre(evento.getNombre())==null){
            evento.setDescripcion(evento.getDescripcion());
            evento = repository.save(evento);
        }
        return evento;
    }

    @Override
    public List<Evento> getAll() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        return (UserDetails)repository.findByNombre(nombre);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Evento info(Long id) {
        return repository.getReferenceById(id);
    }




}


