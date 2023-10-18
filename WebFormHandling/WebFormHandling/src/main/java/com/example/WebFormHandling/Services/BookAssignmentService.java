package com.example.WebFormHandling.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.WebFormHandling.Models.Book;
import com.example.WebFormHandling.Models.Student;
import com.example.WebFormHandling.Models.UserInputForBookAssignmentToStudent;
import com.example.WebFormHandling.Repositories.BookRepository;
import com.example.WebFormHandling.Repositories.StudentRepository;

@Service
public class BookAssignmentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@Transactional
	public boolean attemptBookAssignment(UserInputForBookAssignmentToStudent userInputForBookAssignmentToStudent) {
		Long inputBookId = (long) userInputForBookAssignmentToStudent.getBookId();
    	int inputStudentId = userInputForBookAssignmentToStudent.getStudentId();
	    
    	Optional<Book> bookOptional = bookRepository.findById(inputBookId);
    	Optional<Student> studentOptional = studentRepository.findById(inputStudentId);
    		    
	    if(!studentOptional.isPresent() || !bookOptional.isPresent()) {
	    	return false;
	    }
	    
	    Book book = bookOptional.get();
	    if(book.getBorrowerStudent() != null) {
	    	return false;
	    }
	    
	    Student student = studentOptional.get();
	    student.getBorrowedBooks().add(book);	    
	    studentRepository.save(student);
	    
	    //Added for testing transactions
	    /*
	    int number = 5, zero = 0;
		int quotient = number / zero;
		*/
	    
	    book.setBorrowerStudent(student);
	    bookRepository.save(book);
	    
	    return true;
	}
}
