package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Admin;
import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;

import java.util.List;

public interface IAdminService {
    public Admin create(Admin admin);


    public List<Admin> getAll();

    public void delete(Long id);
}
