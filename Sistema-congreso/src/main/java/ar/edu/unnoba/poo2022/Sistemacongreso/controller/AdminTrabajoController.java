package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ar.edu.unnoba.poo2022.Sistemacongreso.model.Trabajo;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.TrabajoServiceImp;


@Controller
@RequestMapping("admins/eventos/{id_evento}/presentacion")
public class AdminTrabajoController  {

    @Autowired
    private TrabajoServiceImp trabajoService;

    @GetMapping
    public String index(@PathVariable("id_evento") Long id_evento, Model model){
        List<Trabajo> trabajos = trabajoService.findAllByEventoId(id_evento);
        model.addAttribute("trabajos", trabajos);
        return "admins/presentacion/index";
    }
}