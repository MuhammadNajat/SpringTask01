package com.example.WebFormHandling;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {
	
	@GetMapping("/home")
    public String showHomePage() {
	    return "home";
    }

    @GetMapping("/studentRegistration")
    public String showStudentRegistrationForm(UserInputForStudentRegistration userInputForStudentRegistration) {
	    return "studentRegistration";
    }
  
    @PostMapping("/studentRegistration")
    public String processStudentRegistrationFormSubmission(@Valid UserInputForStudentRegistration userInputForStudentRegistration, BindingResult bindingResult) {
	    if(bindingResult.hasErrors()) {
	    	return "studentRegistration";
	    }
	    
	    int inputId = userInputForStudentRegistration.getId();
	    String inputName = userInputForStudentRegistration.getName();
	    String inputEmail = userInputForStudentRegistration.getEmailAddress();
	    
	    Student student = new Student();
	    student.setId(inputId);
	    student.setName(inputName);
	    student.setEmail(inputEmail);
	    student.setBorrowedBooks(new ArrayList<Book>());
	    
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
    
    @GetMapping("/bookRegistration")
    public String showBookRegistrationForm(UserInputForBookRegistration userInputForBookRegistration) {
	    return "bookRegistration";
    }
  
    @PostMapping("/bookRegistration")
    public String processBookRegistrationFormSubmission(@Valid UserInputForBookRegistration userInputForBookRegistration, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
	    	return "bookRegistration";
	    }
	    
	    int inputId = userInputForBookRegistration.getId();
	    String inputName = userInputForBookRegistration.getName();
	    String inputWriterName = userInputForBookRegistration.getWriterName();
	    
	    Book book = new Book();
	    book.setId(inputId);
	    book.setName(inputName);
	    book.setWriterName(inputWriterName);
	    book.setBorrowerStudent(null);
	    
	    SessionFactory sessionFactory = HibernateConfigurationUtils.getSessionFactory();
	    if(sessionFactory == null) {
	    	return "studentRegistration";
	    }
	    
	    Session session = sessionFactory.openSession();
	    Transaction transaction = session.beginTransaction();
	    session.save(book);
	    transaction.commit();
	    
	    return "successCommonPage";
    }
    
    @GetMapping("/bookAssignmentToStudent")
    public String showBookAssignmentToStudentForm(UserInputForBookAssignmentToStudent userInputForBookAssignmentToStudent) {
	    return "bookAssignmentToStudent";
    }
  
    @PostMapping("/bookAssignmentToStudent")
    public String processBookAssignmentToStudentFormSubmission(@Valid UserInputForBookAssignmentToStudent userInputForBookAssignmentToStudent, BindingResult bindingResult) {
	    String templateNameToRender = bindingResult.hasErrors()? "bookAssignmentToStudent" : "successCommonPage";
	    return templateNameToRender;
    }
    
    @GetMapping("/bookWithdrawalFromStudent")
    public String showBookWithdrawalFromStudentForm(UserInputForBookWithdrawalFromStudent userInputForBookWithdrawalFromStudent) {
	    return "bookWithdrawalFromStudent";
    }
  
    @PostMapping("/bookWithdrawalFromStudent")
    public String processBookWithdrawalFromStudentFormSubmission(@Valid UserInputForBookWithdrawalFromStudent userInputForBookWithdrawalFromStudent, BindingResult bindingResult) {
	    String templateNameToRender = bindingResult.hasErrors()? "bookWithdrawalFromStudent" : "successCommonPage";
	    return templateNameToRender;
    }
	
    @GetMapping("/studentBookBorrowingStatus")
    public String showStudentBookBorrowingStatusForm(UserInput userInput, CustomCaptchaData customCaptchaData) {
	    return "studentBookBorrowingStatus";
    }
  
    @PostMapping("/studentBookBorrowingStatus")
    public String processStudentBookBorrowingStatusFormSubmission(@Valid UserInput userInput, BindingResult bindingResult) {
	    String templateNameToRender = bindingResult.hasErrors()? "studentBookBorrowingStatus" : "successCommonPage";
	    return templateNameToRender;
    }
}
