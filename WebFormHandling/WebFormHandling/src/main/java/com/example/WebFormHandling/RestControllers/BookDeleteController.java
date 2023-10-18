package com.example.WebFormHandling.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.Models.UserInputForBookWithdrawalFromStudent;
import com.example.WebFormHandling.Services.BookWithdrawalService;

import jakarta.validation.Valid;

@Controller
public class BookDeleteController {
	@Autowired
	private BookWithdrawalService bookWithdrawalService;
	
	@GetMapping("/bookDelete")
    public String showBookDeletePage() {
	    return "bookDelete";
    }
  
    @PostMapping("/bookDelete/confirm")
    public String processBookDeleteRequest() {

		return "";
    }
}
