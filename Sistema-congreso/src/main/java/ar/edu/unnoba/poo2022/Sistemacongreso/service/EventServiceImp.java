package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;
import ar.edu.unnoba.poo2022.Sistemacongreso.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class EventServiceImp implements IEventService {

    @Autowired
    private EventoRepository repository;

    @Override
    public Evento create(Evento evento) {
        Evento newEvento = repository.save(evento);
        return newEvento;
    }

  
    @Override
    public List<Evento> getAll() { 
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Evento info(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void save(Evento evento) {
        repository.save(evento);
    }
}
