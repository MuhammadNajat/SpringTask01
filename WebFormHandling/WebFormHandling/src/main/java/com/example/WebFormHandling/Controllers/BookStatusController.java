package com.example.WebFormHandling.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.WebFormHandling.Book;
import com.example.WebFormHandling.HibernateConfigurationUtils;
import com.example.WebFormHandling.Student;

@Controller
public class BookStatusController {
	private record BookEntry(Integer id, String name, String author, Integer borrowerStudentId, String borrowerStudentName) {}
	
	@GetMapping("/bookStatus")
    public String showBookStatus(Model model) {
		SessionFactory sessionFactory = HibernateConfigurationUtils.getSessionFactory();
	    if(sessionFactory == null) {
	    	return "studentRegistration";
	    }
	    
	    Session session = sessionFactory.openSession();
	    String queryText = "from Book";
	    Query query = session.createQuery(queryText);
	    List<Book> fetchedBooks = query.list();
	    List<BookEntry> bookEntries = prepareDetailedBookInformation(fetchedBooks);
	    model.addAttribute("books", bookEntries);
	    
	    return "bookStatus";
    }
	
	private List<BookEntry> prepareDetailedBookInformation(List<Book> fetchedBooks) {
		List<BookEntry> bookEntries = new ArrayList<BookEntry>();
	    for(Book book : fetchedBooks) {
	    	Student student = (Student) book.getBorrowerStudent();
	    	Integer borrowerStudentId = student == null? null : student.getId();
	    	String borrowerStudentName = student == null? null : student.getName();
	    	BookEntry bookEntry = new BookEntry(book.getId(), book.getName(), book.getWriterName(), borrowerStudentId, borrowerStudentName);
	    	bookEntries.add(bookEntry);
	    }
	    return bookEntries;
	}
}
