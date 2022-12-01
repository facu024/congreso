package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;
import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

}
