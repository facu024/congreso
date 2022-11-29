package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;

import java.util.List;

public interface IUserService {

    public Usuario create(Usuario usuario);

    public List<Usuario> getAll();

    public void delete(Long id);
}

