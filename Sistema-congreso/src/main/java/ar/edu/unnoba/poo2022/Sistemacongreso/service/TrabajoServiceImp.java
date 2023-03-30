package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import java.util.List;
import ar.edu.unnoba.poo2022.Sistemacongreso.model.Trabajo;
import ar.edu.unnoba.poo2022.Sistemacongreso.repository.TrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


@Service
public class TrabajoServiceImp implements ITrabajoService {
    @Autowired
    private TrabajoRepository repository;

    @Override
    public Trabajo create(Trabajo trabajo) {
        return repository.save(trabajo);
    }

    @Override
    public List<Trabajo> getAll() {
        return repository.findAll(Sort.by("fechaHora").ascending()); 
    }
    @Override
    public Trabajo detalleTrabajo(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void borrar(Long id) {
        repository.deleteById(id);
    }

    public List<Trabajo> findAllByEventoId(Long id_evento) {
        return repository.findAllByEventoId(id_evento);
    }
    public List<Trabajo> findAllByEventoIdAndUsuarioId(Long id_evento,Long id_usuario) {
        return repository.findAllByEventoIdAndUsuarioId(id_evento,id_usuario);
    }
}
