package com.example.WebFormHandling.Controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.Book;
import com.example.WebFormHandling.HibernateConfigurationUtils;
import com.example.WebFormHandling.UserInputForBookRegistration;

import jakarta.validation.Valid;

@Controller
public class BookRegistrationController {
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
	    
	    SessionFactory sessionFactory = HibernateConfigurationUtils.getSessionFactory();
	    if(sessionFactory == null) {
	    	return "bookRegistration";
	    }
	    
	    Session session = sessionFactory.openSession();
	    Transaction transaction = session.beginTransaction();
	    session.save(book);
	    transaction.commit();
	    
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
