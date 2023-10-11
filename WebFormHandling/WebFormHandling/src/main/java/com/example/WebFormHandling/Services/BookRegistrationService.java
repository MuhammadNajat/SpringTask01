package com.example.WebFormHandling.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.WebFormHandling.Models.Book;
import com.example.WebFormHandling.Repositories.BookRepository;

@Service
public class BookRegistrationService {
	@Autowired
	private BookRepository bookRepository;
	
	@Transactional
	public boolean attemptBookRegistration(Book book) {
		boolean attemptSuccessful = false;
		if(bookRepository.findById(book.getId()).isEmpty()) {
			bookRepository.save(book);
			attemptSuccessful = true;
		}
		return attemptSuccessful;
	}
}
