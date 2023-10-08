package com.example.WebFormHandling.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.Book;
import com.example.WebFormHandling.Student;
import com.example.WebFormHandling.UserInputForBookAssignmentToStudent;
import com.example.WebFormHandling.Repositories.BookRepository;
import com.example.WebFormHandling.Repositories.StudentRepository;

import jakarta.validation.Valid;

@Controller
public class BookAssignmentController {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private BookRepository bookRepository;
	
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
	    
    	Optional<Book> bookOptional = bookRepository.findById(inputBookId);
    	Optional<Student> studentOptional = studentRepository.findById(inputStudentId);
	    
	    if(!studentOptional.isPresent() || !bookOptional.isPresent()) {
	    	return "operationFailure";
	    }
	    
	    Book book = bookOptional.get();
	    if(book.getBorrowerStudent() != null) {
	    	return "operationFailure";
	    }
	    
	    Student student = studentOptional.get();
	    student.getBorrowedBooks().add(book);	    
	    studentRepository.save(student);
	    
	    book.setBorrowerStudent(student);
	    bookRepository.save(book);
	    
	    return "successCommonPage";
    }
}
