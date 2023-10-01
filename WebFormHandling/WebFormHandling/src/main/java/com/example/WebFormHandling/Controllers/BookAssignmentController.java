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
import com.example.WebFormHandling.Student;
import com.example.WebFormHandling.UserInputForBookAssignmentToStudent;

import jakarta.validation.Valid;

@Controller
public class BookAssignmentController {
	@GetMapping("/bookAssignmentToStudent")
    public String showBookAssignmentToStudentForm(UserInputForBookAssignmentToStudent userInputForBookAssignmentToStudent) {
	    return "bookAssignmentToStudent";
    }
  
    @PostMapping("/bookAssignmentToStudent")
    public String processBookAssignmentToStudentFormSubmission(@Valid UserInputForBookAssignmentToStudent userInputForBookAssignmentToStudent, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
	    	return "bookAssignmentToStudent";
	    }
	    
    	int inputBookId = userInputForBookAssignmentToStudent.getBookId();
    	int inputStudentId = userInputForBookAssignmentToStudent.getStudentId();
	    
	    SessionFactory sessionFactory = HibernateConfigurationUtils.getSessionFactory();
	    if(sessionFactory == null) {
	    	return "bookAssignmentToStudent";
	    }
	    
	    Session session = sessionFactory.openSession();
	    Transaction transaction = session.beginTransaction();
	    Student student = (Student) session.get(Student.class, inputStudentId);
	    Book book = (Book) session.get(Book.class, inputBookId);
	    
	    if(student == null || book == null || book.getBorrowerStudent() != null) {
	    	return "operationFailure";
	    }
	    
	    student.getBorrowedBooks().add(book);
	    book.setBorrowerStudent(student);
	    
	    transaction.commit();
	    
	    return "successCommonPage";
    }
}
