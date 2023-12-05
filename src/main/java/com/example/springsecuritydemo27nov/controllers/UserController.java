package com.example.springsecuritydemo27nov.controllers;

import com.example.springsecuritydemo27nov.models.User;
import com.example.springsecuritydemo27nov.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
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

}
