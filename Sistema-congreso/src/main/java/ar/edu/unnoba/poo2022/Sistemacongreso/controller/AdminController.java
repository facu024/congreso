package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Admin;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IAdminService;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/admins")
public class AdminController {

    private IAdminService adminService;

    @Autowired
    public AdminController(IAdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/new")
    public String adminNew(Model model) {
        model.addAttribute("admin", new Admin());
        return "admins/new";
    }

    @GetMapping
    public String index(Model model) {
        List<Admin> admins = adminService.getAll();
        model.addAttribute("admins", admins);
        return "admins/index";
    }

    @PostMapping
    public String create(@ModelAttribute Admin administrator) {
        adminService.create(administrator);
        return "redirect:/admins";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         Authentication authentication,
                         RedirectAttributes redirectAttributes){
        Admin adminSesion = (Admin) authentication.getPrincipal();
        if(adminSesion.getId().equals(id)){
            redirectAttributes.addFlashAttribute("message","No te podes borrar");
            return "redirect:/admins";
        }
        adminService.delete(id);
        return "redirect:/admins";
    }
}