package com.testapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.testapp.model.User;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model){
		System.out.println("They are getting here !");
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPage(@ModelAttribute User user, Model model){
		if(user.getUsername().equals("kensuka") && user.getPassword().equals("123456")){
			model.addAttribute("message", "Welcome !");
			return "showMessage";
		}
		
		return "login";
	}
	
}
