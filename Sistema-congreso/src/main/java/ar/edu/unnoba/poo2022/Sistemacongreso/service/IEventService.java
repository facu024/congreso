package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;

import java.util.List;
import java.util.Optional;


public interface IEventService {

    public Evento create (Evento evento);

    public List<Evento> getAll();

    public void delete(Long id);

    //public Evento info(Evento evento, Long id);


    Evento info(Long id);
}
