package com.example.WebFormHandling.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.WebFormHandling.Book;
import com.example.WebFormHandling.Student;
import com.example.WebFormHandling.UserInputForBookWithdrawalFromStudent;
import com.example.WebFormHandling.Repositories.BookRepository;
import com.example.WebFormHandling.Repositories.StudentRepository;

import jakarta.validation.Valid;

@Controller
public class BookWithdrawalController {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private BookRepository bookRepository;
	
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
	    
    	Optional<Student> studentOptional = studentRepository.findById(inputStudentId);
    	Optional<Book> bookOptional = bookRepository.findById(inputBookId);
 
	    
    	if(!studentOptional.isPresent() || !bookOptional.isPresent()) {
	    	return "operationFailure";
	    }
	    
	    Book book = bookOptional.get();
	    
	    Student student = studentOptional.get();
	    student.getBorrowedBooks().remove(book);	    
	    studentRepository.save(student);
	    
	    book.setBorrowerStudent(null);
	    bookRepository.save(book);
	    
	    return "successCommonPage";
    }
}
