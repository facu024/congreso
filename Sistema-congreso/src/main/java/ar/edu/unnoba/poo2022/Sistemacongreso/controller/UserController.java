package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @SuppressWarnings("SpringMVCViewInspection")
    @GetMapping("/new")
    public String userNew(Model model){
        model.addAttribute("usuario",new Usuario());
        return "usuarios/new";
    }

    @PostMapping
    public String create(@ModelAttribute Usuario usuario){
        userService.create(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping
    public String index(Model model, Authentication authentication){
        Usuario usuarioSesion = (Usuario)authentication.getPrincipal();
        model.addAttribute("usuarioSesion",usuarioSesion);
        List<Usuario> usuarios = userService.getAll();
        model.addAttribute("usuarios",usuarios);
        return "usuarios/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Authentication authentication){
        Usuario usuarioSesion = (Usuario)authentication.getPrincipal();
        //la mejor manera de lograr esto seria mediante capa de servicio
        if (usuarioSesion.getId().equals(id)){
            return "redirect:/usuarios";
        }
        userService.delete(id);
        return "redirect:/usuarios";
    }
}


