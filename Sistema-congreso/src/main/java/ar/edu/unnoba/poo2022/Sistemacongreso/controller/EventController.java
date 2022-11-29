package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("eventos")
@Controller
public class EventController {

    private IEventService eventService;


    @Autowired
    public EventController(IEventService eventService) {this.eventService= eventService;}

    @SuppressWarnings("SpringMVCViewInspection")
    @GetMapping("/new")
    public String nuevoEvento (Model model){
        model.addAttribute("evento", new Evento());
        return "/admins/eventos/new";
    }

    @PostMapping
    public String create(@ModelAttribute Evento evento){
        eventService.create(evento);
        return "redirect:/admin/eventos";
    }
}
