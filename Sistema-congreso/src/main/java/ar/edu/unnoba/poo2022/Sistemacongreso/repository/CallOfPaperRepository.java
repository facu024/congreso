package ar.edu.unnoba.poo2022.Sistemacongreso.repository;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.LlamadoPresentacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallOfPaperRepository extends JpaRepository<LlamadoPresentacion,Long> {

}
