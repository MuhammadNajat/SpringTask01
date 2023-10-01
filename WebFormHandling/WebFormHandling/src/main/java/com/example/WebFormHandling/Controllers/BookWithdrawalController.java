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
import com.example.WebFormHandling.UserInputForBookWithdrawalFromStudent;

import jakarta.validation.Valid;

@Controller
public class BookWithdrawalController {
	@GetMapping("/bookWithdrawalFromStudent")
    public String showBookWithdrawalFromStudentForm(UserInputForBookWithdrawalFromStudent userInputForBookWithdrawalFromStudent) {
	    return "bookWithdrawalFromStudent";
    }
  
    @PostMapping("/bookWithdrawalFromStudent")
    public String processBookWithdrawalFromStudentFormSubmission(@Valid UserInputForBookWithdrawalFromStudent userInputForBookWithdrawalFromStudent, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
	    	return "bookWithdrawalFromStudent";
	    }
	    
    	int inputBookId = userInputForBookWithdrawalFromStudent.getBookId();
    	int inputStudentId = userInputForBookWithdrawalFromStudent.getStudentId();
	    
	    SessionFactory sessionFactory = HibernateConfigurationUtils.getSessionFactory();
	    if(sessionFactory == null) {
	    	return "bookWithdrawalFromStudent";
	    }
	    
	    Session session = sessionFactory.openSession();
	    Transaction transaction = session.beginTransaction();
	    Student student = (Student) session.get(Student.class, inputStudentId);
	    Book book = (Book) session.get(Book.class, inputBookId);
	    
	    if(student == null || book == null || book.getBorrowerStudent() != student) {
	    	return "operationFailure";
	    }
	    
	    student.getBorrowedBooks().remove(book);
	    book.setBorrowerStudent(null);
	    
	    transaction.commit();
	    
	    return "successCommonPage";
    }
}
