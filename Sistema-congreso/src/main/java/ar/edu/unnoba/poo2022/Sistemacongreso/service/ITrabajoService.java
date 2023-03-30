package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import java.util.List;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Trabajo;

public interface ITrabajoService {
    public Trabajo create(Trabajo trabajo);
    public List<Trabajo> getAll();
    Trabajo detalleTrabajo(Long id);
    public void borrar(Long id);
}
