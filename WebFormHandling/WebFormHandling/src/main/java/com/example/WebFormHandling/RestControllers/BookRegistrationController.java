package com.example.WebFormHandling.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.Models.Book;
import jakarta.validation.Valid;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

@Controller
public class BookRegistrationController {
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/bookRegistration")
    public String showBookRegistrationForm(Book book) throws UnknownHostException {
	    return "bookRegistration";
    }
  
    @PostMapping("/bookRegistration")
    public String processBookRegistrationFormSubmission(@Valid Book book, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "bookRegistration";
		}
		String url = "http://localhost:8080/data-api/books";
		System.out.println("*** *** Before entrying:");
		System.out.println("Id: " + book.getId() + " name: " + book.getName() + " Author: " + book.getWriterName());
		try {
			ResponseEntity<Book> bookResponseEntity = restTemplate.postForEntity(url, book, Book.class);
			Book response = bookResponseEntity.getBody();
			System.out.println("*** *** After entrying:");
			System.out.println("Id: " + response.getId() + " name: " + response.getName() + " Author: " + response.getWriterName());
		} catch(RestClientException restClientException) {
			System.out.println("Book registration unsuccessful");
			return  "operationFailure";
		}
		return "successCommonPage";
	}
}
