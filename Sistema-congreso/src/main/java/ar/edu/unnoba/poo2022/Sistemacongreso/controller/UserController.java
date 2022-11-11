package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        //noinspection SpringMVCViewInspection
        return "usuarios/new";
    }
 //este comentario es para ver que onda el commit jajaja xd saludis
    @PostMapping
    public String create(@ModelAttribute Usuario usuario){
        //usuario.setApellido("");
        //usuario.setNombre("");
        userService.create(usuario);
        return "redirect:/usuarios";
    }
}