package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IEventService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@RequestMapping("usuarios/eventos")
@Controller
public class UserEventController {

    private IEventService eventService;


    @Autowired
    public UserEventController(IEventService eventService) {this.eventService= eventService;}

    @GetMapping
    public String index(Model model){
        List<Evento> eventos = eventService.getAllEventosActivos();
        model.addAttribute("eventos",eventos);
        return "usuarios/eventos/index";
    }
    @GetMapping("/info/{id}")
    public String info(@PathVariable("id") Long id,Model model) {
        Evento evento= eventService.info(id);
        model.addAttribute("evento",evento);
        return "/usuarios/eventos/info";
    }
}
