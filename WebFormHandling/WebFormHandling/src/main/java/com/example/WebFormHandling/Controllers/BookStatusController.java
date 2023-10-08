package com.example.WebFormHandling.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.WebFormHandling.Book;
import com.example.WebFormHandling.Student;
import com.example.WebFormHandling.Repositories.BookRepository;

@Controller
public class BookStatusController {
	private record BookEntry(Integer id, String name, String author, Integer borrowerStudentId, String borrowerStudentName) {}
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/bookStatus")
    public String showBookStatus(Model model) {
	    Iterable<Book> fetchedBooks = bookRepository.findAll();
	    List<BookEntry> bookEntries = prepareDetailedBookInformation(fetchedBooks);
	    model.addAttribute("books", bookEntries);
	    
	    return "bookStatus";
    }
	
	private List<BookEntry> prepareDetailedBookInformation(Iterable<Book> fetchedBooks) {
		List<BookEntry> bookEntries = new ArrayList<BookEntry>();
	    for(Book book : fetchedBooks) {
	    	Student student = (Student) book.getBorrowerStudent();
	    	System.out.println(">> " + student);
	    	Integer borrowerStudentId = student == null? null : student.getId();
	    	String borrowerStudentName = student == null? null : student.getName();
	    	BookEntry bookEntry = new BookEntry(book.getId(), book.getName(), book.getWriterName(), borrowerStudentId, borrowerStudentName);
	    	bookEntries.add(bookEntry);
	    }
	    return bookEntries;
	}
}
