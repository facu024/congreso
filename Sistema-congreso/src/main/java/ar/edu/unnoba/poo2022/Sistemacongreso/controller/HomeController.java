package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;

@Controller 
public class HomeController {

    @GetMapping({"/","/home"})
    public String goHome(Model model){
        return "home";
    }

    @GetMapping("/register")
    public String goRegister(Model model ){
        model.addAttribute("usuario", new Usuario());
        return "register";
    }
}
