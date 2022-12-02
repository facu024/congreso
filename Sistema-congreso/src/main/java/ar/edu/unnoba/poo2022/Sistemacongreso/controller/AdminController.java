package ar.edu.unnoba.poo2022.Sistemacongreso.controller;

import ar.edu.unnoba.poo2022.Sistemacongreso.model.Admin;
import ar.edu.unnoba.poo2022.Sistemacongreso.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
    @GetMapping("/login")
    public String doLogin(Model model) {
        return "admins/login";
    }

    @PostMapping("/login")
    public String inico(Model model) {
        List<Admin> admins = adminService.getAll();
        model.addAttribute("admins", admins);
        return "redirect:/admins";
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
    public String delete(@PathVariable("id") Long id) {
        adminService.delete(id);
        return "redirect:/usuarios";
    }
}