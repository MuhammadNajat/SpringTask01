package com.example.WebFormHandling.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.WebFormHandling.FormBackingBeans.UserInputForBookRegistration;
import com.example.WebFormHandling.Models.Book;
import com.example.WebFormHandling.Repositories.BookRepository;

@Service
@Transactional
public class BookRegistrationService {
	@Autowired
	private BookRepository bookRepository;
	
	public void attemptBookRegistration(UserInputForBookRegistration userInputForBookRegistration) {
		Book book = prepareBookUsingUserInput(userInputForBookRegistration);
	    bookRepository.save(book);
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
