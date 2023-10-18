package com.example.WebFormHandling.RestControllers;

import com.example.WebFormHandling.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BookSearchController {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/bookSearch")
	public String shpwBookSearchPage() {
		return "bookSearchForm";
	}
	@GetMapping("/bookSearch/result")
	public String getBookById(@RequestParam("bookId") Integer bookId, Model model) {
		Book book = null;
		try {
			///book = restTemplate.getForObject("http://localhost:8080/data-api/books/{bookId}", Book.class, bookId);
			Map<String, Integer> urlVariables = new HashMap<>();
			urlVariables.put("id", bookId);
			URI url = UriComponentsBuilder
					.fromHttpUrl("http://localhost:8080/data-api/books/{id}")
					.build(urlVariables);
			book = restTemplate.getForObject(url, Book.class);
		} catch(RestClientException restClientException) {
			System.out.println("Error in getting book with book id: " + bookId);
		}
		if(book == null) {
			return "operationFailure";
		}
		System.out.println("### ### bookId = " + book.getId());
		model.addAttribute("book", book);
		return "bookSearchResult";
	}
}
