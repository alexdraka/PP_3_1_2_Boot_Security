package com.example.springsecuritydemo27nov.controllers;

import com.example.springsecuritydemo27nov.models.Role;
import com.example.springsecuritydemo27nov.models.User;
import com.example.springsecuritydemo27nov.repositories.RoleRepository;
import com.example.springsecuritydemo27nov.services.UserService;
import com.example.springsecuritydemo27nov.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin")
    public String admin(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());

        return "admin";
    }

    @GetMapping("/admin/people")
    public String mapAllUsers(Model model) {
        model.addAttribute("people", userService.mapAll());

        return "mapAll";
    }

    @GetMapping("/admin/show_people")
    public String show(@RequestParam(defaultValue = "5") Long id, Model model) {
        model.addAttribute("user", userService.show(id));

        return "show";
    }

    @GetMapping("/admin/new_user")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        Collection<Role> roles = roleRepository.findAll();

        model.addAttribute("allRoles", roles);

        return "new";
    }

    @PostMapping("/admin/create_people")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/people";
    }

    @GetMapping("/admin/new_update")
    public String editUser(@RequestParam("id") Long id, Model model) {
            User user = userService.show(id);

            List<Role> roles = roleRepository.findAll();

            model.addAttribute("user", user);
            model.addAttribute("allRoles", roles);

        return "edit";
    }

    @PatchMapping("/admin/edit_people")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);

        return "redirect:/admin/people";
    }

    @GetMapping("/admin/remove_user")
    public String deleteUser(@ModelAttribute("user") User user) {
        return "delete";
    }

    @DeleteMapping("/admin/delete_people")
    public String delete(Long id, @ModelAttribute("user") User user, @ModelAttribute("Role") Role role) {
        userService.delete(id);

        return "redirect:/admin/people";
    }
}
