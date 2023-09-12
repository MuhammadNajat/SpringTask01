package com.example.WebFormHandling;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.validation.Valid;

@Controller
public class RegistrationController  implements WebMvcConfigurer {

  @GetMapping("/registration")
  public String redirectToRegistrationForm(@ModelAttribute User user) {
    return "registrationForm";
  }

  @PostMapping("/registration")
  public String handleSubmitButtonClick(@Valid @ModelAttribute User user, BindingResult bindingResult) {
	  String templateNameToRedirect = bindingResult.hasErrors()? "registrationForm" : "registrationCompleted";
	  return templateNameToRedirect;
  }
	
}
