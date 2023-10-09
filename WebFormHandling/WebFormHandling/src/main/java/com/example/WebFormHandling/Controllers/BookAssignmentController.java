package com.example.WebFormHandling.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.FormBackingBeans.UserInputForBookAssignmentToStudent;
import com.example.WebFormHandling.Services.BookAssignmentService;
import jakarta.validation.Valid;

@Controller
public class BookAssignmentController {
	@Autowired
	private BookAssignmentService bookAssignmentService;
	
	@GetMapping("/bookAssignmentToStudent")
    public String showBookAssignmentToStudentForm(UserInputForBookAssignmentToStudent userInputForBookAssignmentToStudent) {
	    return "bookAssignmentToStudent";
    }
  
    @PostMapping("/bookAssignmentToStudent")
    public String processBookAssignmentToStudentFormSubmission(@Valid UserInputForBookAssignmentToStudent userInputForBookAssignmentToStudent, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
	    	return "bookAssignmentToStudent";
	    }
	    
    	boolean response = bookAssignmentService.attemptBookAssignment(userInputForBookAssignmentToStudent);
    	String templateToRender = response? "successCommonPage" : "operationFailure";
	 
	    return templateToRender;
    }
}
