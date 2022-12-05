package ar.edu.unnoba.poo2022.Sistemacongreso.repository;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Trabajo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajoRepository extends JpaRepository<Trabajo,Long> {

    List<Trabajo> findAllByEventoId(Long id_evento);
    List<Trabajo> findAllByEventoIdAndUsuarioId(Long id_evento, Long id_Usuario);
}
