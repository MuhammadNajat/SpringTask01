package com.example.WebFormHandling.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.Models.Book;
import com.example.WebFormHandling.Services.BookRegistrationService;
import jakarta.validation.Valid;

@Controller
public class BookRegistrationController {
	@Autowired
	private BookRegistrationService bookRegistrationService;
	
	@GetMapping("/bookRegistration")
    public String showBookRegistrationForm(Book book) {
	    return "bookRegistration";
    }
  
    @PostMapping("/bookRegistration")
    public String processBookRegistrationFormSubmission(@Valid Book book, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
	    	return "bookRegistration";
	    }
	    
	    boolean attemptSuccessful = bookRegistrationService.attemptBookRegistration(book);
	    String templateToRender = attemptSuccessful? "successCommonPage" : "operationFailure";
	    return templateToRender;
    }
}
