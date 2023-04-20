package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String index(@PathVariable("id_evento") Long id_evento, Model model, Authentication authentication){
        Usuario usuario = (Usuario)authentication.getPrincipal();
        Evento evento = eventoService.info(id_evento);
        List<Trabajo> trabajos = trabajoService.findAllByEventoIdAndUsuarioId(id_evento, usuario.getId());
        model.addAttribute("trabajos", trabajos);
        model.addAttribute("evento",evento);
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
    public String create(@ModelAttribute Trabajo trabajo,@PathVariable("id_evento") Long id_evento, Model model, Authentication authentication, @RequestParam("file") MultipartFile archivo){
        Usuario usuario = (Usuario)authentication.getPrincipal();
        Evento evento = eventoService.info(id_evento);
        String folder = "/trabajos/";

        if (!archivo.isEmpty()) {
          Path  directorioArchivos = Paths.get("src//main//resources//static"+folder);
          String rutaAbsoluta = directorioArchivos.toFile().getAbsolutePath();
          try {
            byte[] bytesArchivos = archivo.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + archivo.getOriginalFilename());
            Files.write(rutaCompleta,bytesArchivos);

            trabajo.setArchivo(folder+archivo.getOriginalFilename());
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        } 
        trabajo.setUsuario(usuario);
        trabajo.setEvento(evento);
        trabajoService.create(trabajo);
        return "redirect:/usuarios/eventos/"+id_evento+"/presentacion";
    }
    
    @GetMapping("/detalleTrabajo/{idT}")
    public String info(@PathVariable("idT") Long id,@PathVariable("id_evento") Long id_evento,Model model) {
        Trabajo trabajo= trabajoService.info(id);
        model.addAttribute("trabajo",trabajo);
        return "/usuarios/presentacion/detalleTrabajo";
    }
}



