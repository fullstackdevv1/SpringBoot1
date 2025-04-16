package com.home.mycafe.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyCafeControllers {
	
	//Return the welcom-page
	@RequestMapping("/cafe")
	public String showWelcomePage() {
		
		return "welcom-page";
	}
	
	@RequestMapping("/processOrder")
	public String processOrder(HttpServletRequest request,Model model) {
		
		//handle the data from the user
		String userEnteredValue =  request.getParameter("foodType");
		
		//capture the data
		model.addAttribute("userInput", userEnteredValue);
		
		
		//set the data with the model object
		return "processOrder-page";
	}

}
