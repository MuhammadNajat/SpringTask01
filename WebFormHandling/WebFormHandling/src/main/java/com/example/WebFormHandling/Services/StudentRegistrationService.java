package com.example.WebFormHandling.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.WebFormHandling.Models.Student;
import com.example.WebFormHandling.Repositories.StudentRepository;

@Service
public class StudentRegistrationService {
	@Autowired
	private StudentRepository studentRepository;
	
	@Transactional
	public boolean attemptStudentRegistration(Student student) {
		boolean attemptSuccessful = false;
		if(studentRepository.findById(student.getId()).isEmpty()) {
			studentRepository.save(student);
			attemptSuccessful = true;
		}
		return attemptSuccessful;
	}
}
