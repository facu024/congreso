package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;

import java.util.List;


public interface IEventService {

    public Evento create (Evento evento);

    public List<Evento> getAll();

    public void delete(Long id);
}
