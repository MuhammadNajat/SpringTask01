package com.example.WebFormHandling.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.FormBackingBeans.UserInputForStudentRegistration;
import com.example.WebFormHandling.Services.StudentRegistrationService;
import jakarta.validation.Valid;

@Controller
public class StudentRegistrationController {
	@Autowired
	private StudentRegistrationService studentRegistrationService;
	
	@GetMapping("/studentRegistration")
    public String showStudentRegistrationForm(UserInputForStudentRegistration userInputForStudentRegistration) {
	    return "studentRegistration";
    }
  
    @PostMapping("/studentRegistration")
    public String processStudentRegistrationFormSubmission(@Valid UserInputForStudentRegistration userInputForStudentRegistration, BindingResult bindingResult) {
	    if(bindingResult.hasErrors()) {
	    	return "studentRegistration";
	    }
	    
	    studentRegistrationService.attemptStudentRegistration(userInputForStudentRegistration);
	    
	    return "studentRegistrationCompleted";
    }
    
}
