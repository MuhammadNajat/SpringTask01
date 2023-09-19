package com.example.WebFormHandling;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {

  @GetMapping("/registration")
  public String showRegistrationForm(UserInput userInput, CustomCaptchaData customCaptchaData) {
	  return "registrationForm";
  }
  
  @PostMapping("/registration")
  public String processFormSubmission(@Valid UserInput userInput, BindingResult bindingResult, CustomCaptchaData customCaptchaData) {
	  String templateNameToRender = bindingResult.hasErrors()? "registrationForm" : "registrationCompleted";
	  return templateNameToRender;
  }
	
}
