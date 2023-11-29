package com.example.springsecuritydemo27nov.controllers;

import com.example.springsecuritydemo27nov.models.Role;
import com.example.springsecuritydemo27nov.models.User;
import com.example.springsecuritydemo27nov.repositories.RoleRepository;
import com.example.springsecuritydemo27nov.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.Collection;

@Controller
public class PeopleController {
    private final UserServiceImpl userService;
    private final RoleRepository roleRepository;

    @Autowired
    public PeopleController(UserServiceImpl userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);

        model.addAttribute("roles", user.getRoles());

        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        System.out.println(" FROM /USER CONTROLLER: " + userDetails);

        return "user";
    }
    @GetMapping("/admin")
    public String admin(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);

        model.addAttribute("roles", user.getRoles());

        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        System.out.println(" FROM /ADMIN CONTROLLER: " + userDetails);

        return "admin";
    }
    @GetMapping("/admin/people")
    public String index(Model model) {
        model.addAttribute("people", userService.index());

        return "index";
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
    public String editUser(@RequestParam("id") Long id, @ModelAttribute("user") User user, Model model) {
        Collection<Role> roles = roleRepository.findAll();

        model.addAttribute("allRoles", roles);

        return "edit";
    }
    //
    @PostMapping("/admin/edit_people")
    public String update(@RequestParam("id") Long id, Model model, @ModelAttribute("user") User user) {
        userService.update(id, user);

        return "redirect:/admin/people";
    }
    @GetMapping("/admin/remove_user")
    public String deleteUser(@ModelAttribute("user") User user) {
        return "delete";
    }

    @PostMapping("/admin/delete_people")
    public String delete(Long id, @ModelAttribute("user") User user, @ModelAttribute("Role") Role role) {
        userService.delete(id);

        return "redirect:/admin/people";
    }
}
