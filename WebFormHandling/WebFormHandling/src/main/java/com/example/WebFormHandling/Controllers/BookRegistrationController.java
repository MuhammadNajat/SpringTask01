package com.example.WebFormHandling.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.Book;
import com.example.WebFormHandling.UserInputForBookRegistration;
import com.example.WebFormHandling.Repositories.BookRepository;

import jakarta.validation.Valid;

@Controller
public class BookRegistrationController {
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/bookRegistration")
    public String showBookRegistrationForm(UserInputForBookRegistration userInputForBookRegistration) {
	    return "bookRegistration";
    }
  
    @PostMapping("/bookRegistration")
    public String processBookRegistrationFormSubmission(@Valid UserInputForBookRegistration userInputForBookRegistration, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
	    	return "bookRegistration";
	    }
	    
	    Book book = prepareBookUsingUserInput(userInputForBookRegistration);
	    bookRepository.save(book);
	    
	    return "successCommonPage";
    }
    
    private Book prepareBookUsingUserInput(UserInputForBookRegistration userInputForBookRegistration) {
    	int inputId = userInputForBookRegistration.getId();
	    String inputName = userInputForBookRegistration.getName();
	    String inputWriterName = userInputForBookRegistration.getWriterName();
	    
	    Book book = new Book();
	    book.setId(inputId);
	    book.setName(inputName);
	    book.setWriterName(inputWriterName);
	    book.setBorrowerStudent(null);
	    
	    return book;
    }
}
