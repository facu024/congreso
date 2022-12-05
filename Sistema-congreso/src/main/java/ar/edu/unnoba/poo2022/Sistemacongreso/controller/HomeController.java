package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {


    @GetMapping({"/","/home"})
    public String goHome(Model model){
        return "home";
    }

    @GetMapping("/register")
    public String goRegister(Model model){
        return "register";
    }
}
