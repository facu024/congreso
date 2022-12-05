package ar.edu.unnoba.poo2022.Sistemacongreso.repository;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventoRepository extends JpaRepository<Evento,Long> {

    @Query("SELECT e FROM Evento e ORDER BY e.nombre, e.fechaHoraDesde")
    public List<Evento> findAll();

    @Query("SELECT e FROM Evento e WHERE e.fechaHoraHasta >= LOCALTIMESTAMP ORDER BY e.nombre, e.fechaHoraDesde")
    public List<Evento> findAllEventosActivos();

    public Evento findByNombre(String nombre);

    public Evento findById(Evento evento);


}
