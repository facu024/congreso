package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Evento;
import ar.edu.unnoba.poo2022.Sistemacongreso.model.Trabajo;
import ar.edu.unnoba.poo2022.Sistemacongreso.model.Usuario;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.EventServiceImp;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.TrabajoServiceImp;

@Controller
@RequestMapping("usuarios/eventos/{id_evento}/presentacion")
public class UserTrabajoController  {

    @Autowired
    private TrabajoServiceImp trabajoService;
    @Autowired
    private EventServiceImp eventoService;


    @GetMapping
    public String index(@PathVariable("id_evento") Long id_evento, Model model){
        List<Trabajo> trabajos = trabajoService.findAllByEventoId(id_evento);
        model.addAttribute("trabajos", trabajos);
        return "usuarios/presentacion/index";
    }
    @GetMapping("/new")
    public String nuevoTrabajo(@PathVariable("id_evento") Long id_evento, Model model, Authentication authentication ){
        Evento evento = eventoService.info(id_evento);
        Trabajo trabajo = new Trabajo();
        trabajo.setEvento(evento);
        model.addAttribute("trabajo", trabajo);
        return "usuarios/presentacion/new";
    }

    @PostMapping
    public String create(@ModelAttribute Trabajo trabajo,@PathVariable("id_evento") Long id_evento, Model model, Authentication authentication){
        Usuario usuario = (Usuario)authentication.getPrincipal();
        Evento evento = eventoService.info(id_evento);
        trabajo.setUsuario(usuario);
        trabajo.setEvento(evento);
        trabajoService.create(trabajo);
        return "redirect:/usuarios/eventos/"+id_evento+"/presentacion";
    }
}
