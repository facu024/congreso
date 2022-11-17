package ar.edu.unnoba.poo2022.Sistemacongreso.service;

import ar.edu.unnoba.poo2022.Sistemacongreso.config.AdminSecurityConfig;
import ar.edu.unnoba.poo2022.Sistemacongreso.model.Admin;
import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;
import ar.edu.unnoba.poo2022.Sistemacongreso.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//tenof que terminar admin security config
@Service
public class AdminServiceImp implements IAdminService, UserDetailsService{
    @Autowired
    private AdminRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    @Override
    public Admin create(Admin admin) {
        if(repository.findByEmail(admin.getEmail())==null){
            admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
            admin = repository.save(admin);
        }
        return admin;
    }

    @Override
    public List<Admin> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}

