package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/eventos")
@Controller
public class EventController {

    private IEventService eventService;


    @Autowired
    public EventController(IEventService eventService) {this.eventService= eventService;}

    @SuppressWarnings("SpringMVCViewInspection")
    @GetMapping("/new")
    public String nuevoEvento (Model model){
        model.addAttribute("evento", new Evento());
        return "usuarios/eventos/new";
    }

    @PostMapping
    public String create(@ModelAttribute Evento evento){
        eventService.create(evento);
        return "redirect:/eventos";
    }
    @GetMapping
    public String index(Model model){
        List<Evento> eventos = eventService.getAll();
        model.addAttribute("eventos",eventos);

        return "usuarios/eventos/index";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){

        eventService.delete(id);
        return "redirect:/eventos";
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable("id") Long id,Model model) {
        Evento evento1 = eventService.info(id);
        model.addAttribute("evento",evento1);
        return "/usuarios/eventos/info";
    }

}
