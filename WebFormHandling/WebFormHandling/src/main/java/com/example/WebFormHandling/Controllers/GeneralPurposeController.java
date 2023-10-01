package com.example.WebFormHandling.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralPurposeController {
	@GetMapping("/home")
    public String showStudentBookBorrowingStatusForm() {
	    return "home";
    }
}
