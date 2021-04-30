package com.poc6.neosoft.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.poc6.neosoft.model.User;
import com.poc6.neosoft.service.UserService;
import org.slf4j.Logger;


@Controller
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;
//	private final UserService userService;
//	
//	@Autowired
//	UserController(UserService userService){
//		this.userService = userService;
//		logger.info("Controller is ready");
//	}
	
	@GetMapping("/")
	public String viewHomePage(Model model,@Param("keyword") String keyword) {
		List<User> listUsers = userService.getAllUsers(keyword);
		model.addAttribute("listUsers", listUsers);
		model.addAttribute("keyword",keyword);
		logger.info("Get all users");
		return "index";
		
		//return findPaginated(1, "firstName", "asc", model);
		}

	@GetMapping("/showNewUserForm")
	public String showNewUserForm(Model model) {
		// create model attribute to bind form data
		User user = new User();
		model.addAttribute("user", user);
		return "new_user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@Valid User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.error("Invalid inputs...!!");
			return "new_user";
		}
		logger.info("Saving new user...");
		userService.saveUser(user);
		return "redirect:/";
	}
//	@PostMapping("/saveUser")
//	public String saveUser(@ModelAttribute("user") User user) {
//		// save user to database
//		userService.saveUser(user);
//		logger.info("User information is saved");
//		return "redirect:/";
//	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get user from the service
		User user = userService.getUserById(id);
		
		// set user as a model attribute to pre-populate the form
		model.addAttribute("user", user);
		return "update_user";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable (value = "id") long id) {
		
		// call delete user method 
		this.userService.deleteUserById(id);
	   logger.info("User is deleted");
		return "redirect:/";
	}

}
