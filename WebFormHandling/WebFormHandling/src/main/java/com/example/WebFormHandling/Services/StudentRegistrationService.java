package com.example.WebFormHandling.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.WebFormHandling.FormBackingBeans.UserInputForStudentRegistration;
import com.example.WebFormHandling.Models.Book;
import com.example.WebFormHandling.Models.Student;
import com.example.WebFormHandling.Repositories.StudentRepository;

@Service
public class StudentRegistrationService {
	@Autowired
	private StudentRepository studentRepository;
	
	@Transactional
	public void attemptStudentRegistration(UserInputForStudentRegistration userInputForStudentRegistration) {
		Student student = prepareStudentUsingUserInput(userInputForStudentRegistration);
	    studentRepository.save(student);
	}
    
	private Student prepareStudentUsingUserInput(UserInputForStudentRegistration userInputForStudentRegistration) {
    	int inputId = userInputForStudentRegistration.getId();
	    String inputName = userInputForStudentRegistration.getName();
	    String inputEmail = userInputForStudentRegistration.getEmailAddress();
	    
	    Student student = new Student();
	    student.setId(inputId);
	    student.setName(inputName);
	    student.setEmail(inputEmail);
	    student.setBorrowedBooks(new ArrayList<Book>());
	    
	    return student;
    }
}
