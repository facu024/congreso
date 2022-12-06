package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IUserService;

@Controller 
public class HomeController {
    private final IUserService userService;

    @Autowired
    public HomeController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping({"/","/home"})
    public String goHome(Model model){
        return "home";
    }
    @GetMapping("/register")
    public String goRegister(Model model ){
        model.addAttribute("usuario",new Usuario());
        return "/register";
    }
    @PostMapping("/register")
    public String regisrarUsuario( @ModelAttribute Usuario usuario){
        userService.create(usuario);
        return "home";
    }

}
