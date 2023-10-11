package com.example.WebFormHandling.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.WebFormHandling.Models.Book;
import com.example.WebFormHandling.Models.Student;
import com.example.WebFormHandling.Models.UserInputForBookWithdrawalFromStudent;
import com.example.WebFormHandling.Repositories.BookRepository;
import com.example.WebFormHandling.Repositories.StudentRepository;

@Service
public class BookWithdrawalService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private BookRepository bookRepository;
	
	@Transactional
	public boolean attemptBookWithdrawal(UserInputForBookWithdrawalFromStudent userInputForBookWithdrawalFromStudent) {
		int inputBookId = userInputForBookWithdrawalFromStudent.getBookId();
    	int inputStudentId = userInputForBookWithdrawalFromStudent.getStudentId();
	    
    	Optional<Student> studentOptional = studentRepository.findById(inputStudentId);
    	Optional<Book> bookOptional = bookRepository.findById(inputBookId);
 
	    
    	if(!studentOptional.isPresent() || !bookOptional.isPresent()) {
	    	return false;
	    }
	    
	    Book book = bookOptional.get();
	    
	    Student student = studentOptional.get();
	    student.getBorrowedBooks().remove(book);	    
	    studentRepository.save(student);
	    
	    book.setBorrowerStudent(null);
	    bookRepository.save(book);
	    
	    return true;
	}
}
