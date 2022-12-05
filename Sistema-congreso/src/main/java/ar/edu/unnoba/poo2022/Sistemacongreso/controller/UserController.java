package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admins/usuarios")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }
 

    @GetMapping("/new")
    public String userNew(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admins/new_user";
    }

    @PostMapping
    public String createUser(@ModelAttribute Usuario usuario){
        userService.create(usuario);
        return "redirect:/admins/usuarios";
    }

    @GetMapping
    public String indexUser(Model model){
        List<Usuario> usuarios = userService.getAll();
        model.addAttribute("usuarios",usuarios);
        return "admins/index_user";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admins/usuarios";
    }
}





