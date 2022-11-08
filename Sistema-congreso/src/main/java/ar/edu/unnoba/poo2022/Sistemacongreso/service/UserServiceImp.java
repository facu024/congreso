package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;
import ar.edu.unnoba.poo2022.Sistemacongreso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements IUserService{

    @Autowired
    private UserRepository repository;

    @Override
    public Usuario create(Usuario usuario) {
        if (repository.findByUserName(usuario.getNombre()) == null) {
            usuario = repository.save(usuario);
        }
        return usuario;
    }

        @Override
        public List<Usuario> getAll() {
            return repository.findAll();
        }

        @Override
        public void delete(Long id) {
            repository.deleteById(id);
        }


    }

