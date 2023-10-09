package com.example.WebFormHandling.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.FormBackingBeans.UserInputForBookRegistration;
import com.example.WebFormHandling.Services.BookRegistrationService;
import jakarta.validation.Valid;

@Controller
public class BookRegistrationController {
	@Autowired
	private BookRegistrationService bookRegistrationService;
	
	@GetMapping("/bookRegistration")
    public String showBookRegistrationForm(UserInputForBookRegistration userInputForBookRegistration) {
	    return "bookRegistration";
    }
  
    @PostMapping("/bookRegistration")
    public String processBookRegistrationFormSubmission(@Valid UserInputForBookRegistration userInputForBookRegistration, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
	    	return "bookRegistration";
	    }
	    
	    bookRegistrationService.attemptBookRegistration(userInputForBookRegistration);
	    
	    return "successCommonPage";
    }
}
