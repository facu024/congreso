package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        List<Usuario> usuarios = userService.getAll();
        model.addAttribute("usuarios",usuarios);
        model.addAttribute("usuarioSesion",usuarioSesion);
        return "usuarios/index";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         Authentication authentication,
                         RedirectAttributes redirectAttributes){
        Usuario usuarioSesion = (Usuario) authentication.getPrincipal();
        if(usuarioSesion.getId().equals(id)){
            redirectAttributes.addFlashAttribute("message","No te podes borrar");
            return "redirect:/usuarios";
        }
        userService.delete(id);
        return "redirect:/usuarios";
    }

}





