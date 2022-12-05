package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IEventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("admins/eventos")
@Controller
public class AdminEventController {

    private IEventService eventService;


    @Autowired
    public AdminEventController(IEventService eventService) {this.eventService= eventService;}

    @SuppressWarnings("SpringMVCViewInspection")
  

    @PostMapping
    public String create(@ModelAttribute Evento evento){
        eventService.create(evento);
        return "redirect:/admins/eventos";
    }

    @GetMapping
    public String index(Model model){
        List<Evento> eventos = eventService.getAll();
        model.addAttribute("eventos",eventos);
        return "admins/eventos/index";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        eventService.delete(id);
        return "redirect:/admins/eventos";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable("id") Long id,Model model) {
        Evento evento= eventService.info(id);
        model.addAttribute("evento",evento);
        return "/admins/eventos/info";
    }
    @GetMapping("/new")
    public String nuevoEvento (Model model){
        model.addAttribute("evento", new Evento());
        return "/admins/eventos/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Evento evento= eventService.info(id);
        model.addAttribute("evento",evento);
        return "/admins/eventos/edit";
    }
    @PostMapping("/{id}")
    public String save(@ModelAttribute Evento evento) {
        eventService.save(evento);
        return "redirect:/admins/eventos";
    }
}
