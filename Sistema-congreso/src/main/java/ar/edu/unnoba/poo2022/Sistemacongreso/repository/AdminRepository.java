package ar.edu.unnoba.poo2022.Sistemacongreso.repository;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    public Admin findByUserName(String userName);

}
