package com.example.springsecuritydemo27nov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		messages.add("6.0.9 version by oct'23 ");
		messages.add("To see all people available click on the next page ");
		model.addAttribute("messages", messages);
		return "hello";
	}
}