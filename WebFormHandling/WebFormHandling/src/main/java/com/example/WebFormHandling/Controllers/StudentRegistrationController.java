package com.example.WebFormHandling.Controllers;

import java.util.ArrayList;

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
import com.example.WebFormHandling.UserInputForStudentRegistration;

import jakarta.validation.Valid;

@Controller
public class StudentRegistrationController {
	@GetMapping("/studentRegistration")
    public String showStudentRegistrationForm(UserInputForStudentRegistration userInputForStudentRegistration) {
	    return "studentRegistration";
    }
  
    @PostMapping("/studentRegistration")
    public String processStudentRegistrationFormSubmission(@Valid UserInputForStudentRegistration userInputForStudentRegistration, BindingResult bindingResult) {
	    if(bindingResult.hasErrors()) {
	    	return "studentRegistration";
	    }
	    
	    Student student = prepareStudentUsingUserInput(userInputForStudentRegistration);
	    
	    SessionFactory sessionFactory = HibernateConfigurationUtils.getSessionFactory();
	    if(sessionFactory == null) {
	    	return "studentRegistration";
	    }
	    
	    Session session = sessionFactory.openSession();
	    Transaction transaction = session.beginTransaction();
	    session.save(student);
	    transaction.commit();
	    
	    return "studentRegistrationCompleted";
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
