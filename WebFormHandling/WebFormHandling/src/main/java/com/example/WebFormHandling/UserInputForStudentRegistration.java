package com.example.WebFormHandling;

import org.springframework.stereotype.Component;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
public class UserInputForStudentRegistration {
	@NotNull
	@Min(1)
	private int id;
	
	@NotNull
	@Size(min=3, max=30)
	@Pattern(regexp = "^[a-zA-Z\s]*$")
	private String name;
	
	@NotNull
	@NotEmpty
	@Email
	private String emailAddress;

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
