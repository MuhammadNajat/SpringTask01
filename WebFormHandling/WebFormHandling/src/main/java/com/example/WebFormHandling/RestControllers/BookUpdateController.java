package com.example.WebFormHandling.RestControllers;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.WebFormHandling.Models.Book;
import com.example.WebFormHandling.Models.Student;
import com.example.WebFormHandling.Repositories.BookRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
public class BookUpdateController {
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/bookUpdate")
    public String showBookUpdatePageBasedOnInputBookId(@RequestParam("bookId") Integer bookId, Model model) {
	    Book book = attemptToFindBook(bookId);
		if(book == null) {
			return "operationFailure";
		}

		model.addAttribute("bookId", bookId);
		model.addAttribute("bookFound", book);
		return "bookUpdate";
    }

	private Book attemptToFindBook(Integer bookId) {
		Book book = null;
		try {
			book = restTemplate.getForObject("http://localhost:8080/data-api/books/{bookId}", Book.class, bookId);
		} catch(RestClientException restClientException) {
			System.out.println("Error in getting book with book id: " + bookId);
			return null;
		}
		return book;
	}

	@PostMapping("/bookUpdate")
	public String processBookUpdateFormSubmission(@Valid Book book, BindingResult bindingResult, @RequestParam("bookId") Integer bookId) {
		if(bindingResult.hasErrors()) {
			return "bookUpdate";
		}
		try {
			restTemplate.put("http://localhost:8080/data-api/books/{id}", book, bookId);
		}
		catch (RestClientException restClientException) {
			System.out.println("Book updating unsuccessful");
			return "bookUpdate";
		}
		return "successCommonPage";
	}
}
