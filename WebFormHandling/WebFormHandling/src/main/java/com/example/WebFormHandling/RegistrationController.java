package com.example.WebFormHandling;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	    
	    Integer inputId = userInputForStudentRegistration.getId();
	    String inputName = userInputForStudentRegistration.getName();
	    String inputEmail = userInputForStudentRegistration.getEmailAddress();
	    
	    Student student = new Student();
	    student.setId(inputId);
	    student.setName(inputName);
	    student.setEmail(inputEmail);
	    student.setBorrowedBooks(new ArrayList<Book>());
	    
	    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	    if(sessionFactory == null) {
	    	return "studentRegistration";
	    }
	    Session session = sessionFactory.getCurrentSession();
	    
	    session.beginTransaction();
	    session.save(student);
	    session.getTransaction().commit();
	    
	    return "studentRegistrationCompleted";
    }
    
    @GetMapping("/bookRegistration")
    public String showBookRegistrationForm() {
	    return "bookRegistration";
    }
  
    @PostMapping("/bookRegistration")
    public String processBookRegistrationFormSubmission(@Valid UserInput userInput, BindingResult bindingResult) {
	    String templateNameToRender = bindingResult.hasErrors()? "bookRegistration" : "successCommonPage";
	    return templateNameToRender;
    }
    
    @GetMapping("/bookAssignmentToStudent")
    public String showBookAssignmentToStudentForm(UserInput userInput) {
	    return "bookAssignmentToStudent";
    }
  
    @PostMapping("/bookAssignmentToStudent")
    public String processBookAssignmentToStudentFormSubmission(@Valid UserInput userInput, BindingResult bindingResult) {
	    String templateNameToRender = bindingResult.hasErrors()? "bookAssignmentToStudent" : "successCommonPage";
	    return templateNameToRender;
    }
    
    @GetMapping("/bookWithdrawalFromStudent")
    public String showBookWithdrawalFromStudentForm(UserInput userInput, CustomCaptchaData customCaptchaData) {
	    return "bookWithdrawalFromStudent";
    }
  
    @PostMapping("/bookWithdrawalFromStudent")
    public String processBookWithdrawalFromStudentFormSubmission(@Valid UserInput userInput, BindingResult bindingResult) {
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
