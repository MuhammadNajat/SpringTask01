package com.example.WebFormHandling.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.Models.UserInputForBookWithdrawalFromStudent;
import com.example.WebFormHandling.Services.BookWithdrawalService;

import jakarta.validation.Valid;

@Controller
public class BookWithdrawalController {
	@Autowired
	private BookWithdrawalService bookWithdrawalService;
	
	@GetMapping("/bookWithdrawalFromStudent")
    public String showBookWithdrawalFromStudentForm(UserInputForBookWithdrawalFromStudent userInputForBookWithdrawalFromStudent) {
	    return "bookWithdrawalFromStudent";
    }
  
    @PostMapping("/bookWithdrawalFromStudent")
    public String processBookWithdrawalFromStudentFormSubmission(@Valid UserInputForBookWithdrawalFromStudent userInputForBookWithdrawalFromStudent, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
	    	return "bookWithdrawalFromStudent";
	    }
	    
    	boolean response = bookWithdrawalService.attemptBookWithdrawal(userInputForBookWithdrawalFromStudent);
        return response? "successCommonPage" : "operationFailure";
    }
}
