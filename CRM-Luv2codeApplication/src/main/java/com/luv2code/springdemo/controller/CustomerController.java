package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//need to inject the customer service
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		//get the customers from servicelayer		
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add the customers to model
		model.addAttribute("customers", theCustomers);

		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		//save the customer using our service
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,Model model) {
		
		//get the customer from our service
		Customer customer = customerService.getCustomer(theId);
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}

	@GetMapping("/showFormForDelete")
	public String showFormForDelete(@RequestParam("customerId") int theId) {
		
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}
