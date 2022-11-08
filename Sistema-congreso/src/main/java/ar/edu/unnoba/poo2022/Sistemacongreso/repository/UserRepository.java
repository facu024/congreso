package ar.edu.unnoba.poo2022.Sistemacongreso.repository;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario,Long> {
    public Usuario findByUserName(String nombre);

}
